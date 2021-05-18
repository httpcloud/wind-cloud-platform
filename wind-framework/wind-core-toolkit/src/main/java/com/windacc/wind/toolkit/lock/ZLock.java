package com.windacc.wind.toolkit.lock;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:27
 */
@AllArgsConstructor
public class ZLock implements AutoCloseable {
    @Getter
    private final Object lock;

    private final DistributedLock locker;

    @Override
    public void close() throws Exception {
        locker.unlock(lock);
    }
}
