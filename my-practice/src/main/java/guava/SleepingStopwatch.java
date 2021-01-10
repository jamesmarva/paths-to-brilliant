package guava;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.RateLimiter;
import com.google.common.util.concurrent.Uninterruptibles;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 21.1.4 20:50
 */
public abstract class SleepingStopwatch {
    /**
     * Constructor for use by subclasses.
     */
    protected SleepingStopwatch() {
    }

    /*
     * We always hold the mutex when calling this. TODO(cpovirk): Is that important? Perhaps we need
     * to guarantee that each call to reserveEarliestAvailable, etc. sees a value >= the previous?
     * Also, is it OK that we don't hold the mutex when sleeping?
     */
    protected abstract long readMicros();

    protected abstract void sleepMicrosUninterruptibly(long micros);

    public static SleepingStopwatch createFromSystemTimer() {
        return new SleepingStopwatch() {
            final Stopwatch stopwatch = Stopwatch.createStarted();

            @Override
            protected long readMicros() {
                return stopwatch.elapsed(MICROSECONDS);
            }

            @Override
            protected void sleepMicrosUninterruptibly(long micros) {
                if (micros > 0) {
                    Uninterruptibles.sleepUninterruptibly(micros, MICROSECONDS);
                }
            }
        };
    }
}