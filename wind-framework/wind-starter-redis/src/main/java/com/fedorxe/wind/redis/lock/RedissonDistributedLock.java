package com.fedorxe.wind.redis.lock;

import com.fedorxe.wind.tool.constants.CommonConstant;
import com.fedorxe.wind.tool.exception.LockException;
import com.fedorxe.wind.tool.lock.DistributedLock;
import com.fedorxe.wind.tool.lock.ZLock;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.util.concurrent.TimeUnit;

/**
 * <p>Description desc   </p>
 *
 * @author fedorxe
 * @date 2021/5/15 10:30
 */
@Slf4j
@ConditionalOnClass(RedissonClient.class)
public class RedissonDistributedLock implements DistributedLock {
    @Autowired
    private RedissonClient redisson;

    public RedissonDistributedLock() {
        log.info("启动 RedissonDistributedLock");
    }

    private ZLock getLock(String key, boolean isFair) {
        RLock lock;
        if (isFair) {
            lock = redisson.getFairLock(CommonConstant.LOCK_KEY_PREFIX + key);
        } else {
            lock =  redisson.getLock(CommonConstant.LOCK_KEY_PREFIX + key);
        }
        return new ZLock(lock, this);
    }

    @Override
    public ZLock lock(String key, long leaseTime, TimeUnit unit, boolean isFair) {
        ZLock zLock = getLock(key, isFair);
        RLock lock = (RLock)zLock.getLock();
        lock.lock(leaseTime, unit);
        return zLock;
    }

    @Override
    public ZLock tryLock(String key, long waitTime, long leaseTime, TimeUnit unit, boolean isFair) throws InterruptedException {
        ZLock zLock = getLock(key, isFair);
        RLock lock = (RLock)zLock.getLock();
        if (lock.tryLock(waitTime, leaseTime, unit)) {
            return zLock;
        }
        return null;
    }

    @Override
    public void unlock(Object lock) {
        if (lock != null) {
            if (lock instanceof RLock) {
                RLock rLock = (RLock)lock;
                if (rLock.isLocked()) {
                    rLock.unlock();
                }
            } else {
                throw new LockException("requires RLock type");
            }
        }
    }
}
