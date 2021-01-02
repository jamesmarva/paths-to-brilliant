package ch38;

import com.google.common.math.LongMath;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 21.1.2 19:27
 */
public class NewRateLimiter {
    private final double maxBurstSeconds;

    private final SleepingStopwatch stopwatch;

    private volatile @Nullable Object mutexDoNotUseDirectly;

    private long nextFreeTicketMicros = 0L;

    /** The currently stored permits. */
    private double storedPermits;

    /** The maximum number of stored permits. */
    private double maxPermits;

    /**
     * The interval between two unit requests, at our stable rate. E.g., a stable rate of 5 permits
     * per second has a stable interval of 200ms.
     */
    private double stableIntervalMicros;

    public NewRateLimiter(SleepingStopwatch stopwatch) {
        this(stopwatch, 1.0);
    }

    public NewRateLimiter(SleepingStopwatch stopwatch, double maxBurstSeconds) {
        this.stopwatch = stopwatch;
        this.maxBurstSeconds = maxBurstSeconds;
    }


    public static NewRateLimiter create(double permitsPerSecond) {

        return create(permitsPerSecond, SleepingStopwatch.createFromSystemTimer());
    }

    public static NewRateLimiter create(double permitsPerSecond, SleepingStopwatch stopwatch) {
        NewRateLimiter newRateLimiter = new NewRateLimiter(stopwatch, 1.0);
        newRateLimiter.setRate(permitsPerSecond);
        return newRateLimiter;
    }

    public double acquire() {
        return acquire(1);
    }

    public double acquire(int permits) {
        long microsToWait = reserve(permits);
        stopwatch.sleepMicrosUninterruptibly(microsToWait);
        return 1.0 * microsToWait / SECONDS.toMicros(1L);
    }

    public boolean tryAcquire(int permits, long timeout, TimeUnit unit) {
        long timeoutMicros = max(unit.toMicros(timeout), 0);
        checkPermits(permits);
        long microsToWait;
        synchronized (mutex()) {
            long nowMicros = stopwatch.readMicros();
            if (!canAcquire(nowMicros, timeoutMicros)) {
                return false;
            } else {
                microsToWait = reserveAndGetWaitLength(permits, nowMicros);
            }
        }
        stopwatch.sleepMicrosUninterruptibly(microsToWait);
        return true;
    }

    private boolean canAcquire(long nowMicros, long timeoutMicros) {
        return queryEarliestAvailable(nowMicros) - timeoutMicros <= nowMicros;
    }


    private final long reserve(int permits) {
        checkPermits(permits);
        synchronized (mutex()) {
            return reserveAndGetWaitLength(permits, stopwatch.readMicros());
        }
    }

    private final long reserveAndGetWaitLength(int permits, long nowMicros) {
        long momentAvailable = reserveEarliestAvailable(permits, nowMicros);
        return max(momentAvailable - nowMicros, 0);
    }

    private long reserveEarliestAvailable(int requiredPermits, long nowMicros) {
        resync(nowMicros);
        long returnValue = nextFreeTicketMicros;
        double storedPermitsToSpend = min(requiredPermits, this.storedPermits);
        double freshPermits = requiredPermits - storedPermitsToSpend;
        long waitMicros =
                storedPermitsToWaitTime(this.storedPermits, storedPermitsToSpend)
                        + (long) (freshPermits * stableIntervalMicros);

        this.nextFreeTicketMicros = LongMath.saturatedAdd(nextFreeTicketMicros, waitMicros);
        this.storedPermits -= storedPermitsToSpend;
        return returnValue;
    }

    private long storedPermitsToWaitTime(double storedPermits, double permitsToTake) {
        return 0L;
    }

    private static void checkPermits(int permits) {
        checkArgument(permits > 0, "Requested permits (%s) must be positive", permits);
    }


    final void doSetRate(double permitsPerSecond, long nowMicros) {
        resync(nowMicros);
        double stableIntervalMicros = SECONDS.toMicros(1L) / permitsPerSecond;
        this.stableIntervalMicros = stableIntervalMicros;
        doSetRate(permitsPerSecond, stableIntervalMicros);
    }

    void doSetRate(double permitsPerSecond, double stableIntervalMicros) {
        double oldMaxPermits = this.maxPermits;
        maxPermits = maxBurstSeconds * permitsPerSecond;
        if (oldMaxPermits == Double.POSITIVE_INFINITY) {
            // if we don't special-case this, we would get storedPermits == NaN, below
            storedPermits = maxPermits;
        } else {
            storedPermits =
                    (oldMaxPermits == 0.0)
                            ? 0.0 // initial state
                            : storedPermits * maxPermits / oldMaxPermits;
        }
    }

    public final void setRate(double permitsPerSecond) {
        checkArgument(
                permitsPerSecond > 0.0 && !Double.isNaN(permitsPerSecond), "rate must be positive");
        synchronized (mutex()) {
            doSetRate(permitsPerSecond, stopwatch.readMicros());
        }
    }

    /**
     * Returns the stable rate (as {@code permits per seconds}) with which this {@code RateLimiter} is
     * configured with. The initial value of this is the same as the {@code permitsPerSecond} argument
     * passed in the factory method that produced this {@code RateLimiter}, and it is only updated
     * after invocations to {@linkplain #setRate}.
     */
    public final double getRate() {
        synchronized (mutex()) {
            return doGetRate();
        }
    }

    final long queryEarliestAvailable(long nowMicros) {
        return nextFreeTicketMicros;
    }

    public final double doGetRate() {
        return SECONDS.toMicros(1L) / stableIntervalMicros;
    }

    private void resync(long nowMicros) {
        // if nextFreeTicket is in the past, resync to now
        if (nowMicros > nextFreeTicketMicros) {
            double newPermits = (nowMicros - nextFreeTicketMicros) / coolDownIntervalMicros();
            storedPermits = min(maxPermits, storedPermits + newPermits);
            nextFreeTicketMicros = nowMicros;
        }
    }

    private double coolDownIntervalMicros()  {
        return stableIntervalMicros;
    }

    private Object mutex() {
        Object mutex = mutexDoNotUseDirectly;
        if (mutex == null) {
            synchronized (this) {
                mutex = mutexDoNotUseDirectly;
                if (mutex == null) {
                    mutexDoNotUseDirectly = mutex = new Object();
                }
            }
        }
        return mutex;
    }

    static long toNanosSaturated(Duration duration) {
        // Using a try/catch seems lazy, but the catch block will rarely get invoked (except for
        // durations longer than approximately +/- 292 years).
        try {
            return duration.toNanos();
        } catch (ArithmeticException tooBig) {
            return duration.isNegative() ? Long.MIN_VALUE : Long.MAX_VALUE;
        }
    }
}
