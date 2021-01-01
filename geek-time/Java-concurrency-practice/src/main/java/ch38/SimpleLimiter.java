package ch38;

import java.util.concurrent.TimeUnit;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 21.1.1 17:12
 */
public class SimpleLimiter {

    private long currentStoredPermit = 0;

    private long maxPermit = 3;

    long next = System.nanoTime();

    // 发放令牌间隔：纳秒
    long interval = 1000_000_000;


    /**
     * 同步时间
     * @param now 同步的时间
     */
    void resync(long now) {
        // 在预订的时间之后请求的数据
        if (now > next) {
            // 在上次的next到now为止，到底产生了多少的permit
            long newPermits = (now - next) / interval;

            currentStoredPermit = Math.min(maxPermit, currentStoredPermit + newPermits);
            next =now;
        }
    }


    /**
     * 预占令牌
     * @param now
     * @return
     */
    synchronized long reverse(long now) {
        resync(now);
        // 获取令牌的时间
        long at = next;

        // 令牌桶中能提供的令牌
        long fb = Math.min(1, currentStoredPermit);

        // 令牌净需求：首先减掉令牌桶中的令牌
        long nr = 1 - fb;

        // 重新计算下一令牌产生时间
        next = next + nr * interval;

        // 重新计算令牌桶中的令牌
        this.currentStoredPermit -= fb;

        return at;
    }

    void acquire() {
        long now = System.nanoTime();

        long at = reverse(now);
        long waitTime = Math.max(at - now, 0);

        if (waitTime > 0) {
            try {
                TimeUnit.NANOSECONDS.sleep(waitTime);
            } catch (InterruptedException e) {

            }
        }
    }
}
