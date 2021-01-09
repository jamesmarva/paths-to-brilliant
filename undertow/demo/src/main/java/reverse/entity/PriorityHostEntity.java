package reverse.entity;

import lombok.Data;
import reverse.Host;
import reverse.limiter.SmoothBurstyRateLimiter;

/**
 * 〈功能概述〉<br>
 *
 * @author 王涵威
 * @date 21.1.9 16:19
 */
@Data
public class PriorityHostEntity {

    private Host host;

    private SmoothBurstyRateLimiter limiter;

    public PriorityHostEntity(Host host, SmoothBurstyRateLimiter limiter) {
        this.host = host;
        this.limiter = limiter;
    }

    @Override
    public String toString() {
        return "PriorityHostEntity{" +
                "host=" + host +
                ", limiter=" + limiter +
                '}';
    }
}
