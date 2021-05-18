package com.windacc.wind.ribbon.strategy;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.Callable;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/18
 */
@Slf4j
public class RequestConcurrencyStrategy extends HystrixConcurrencyStrategy {

    public RequestConcurrencyStrategy() {
        // 通过HystrixPlugins注册当前扩展的HystrixConcurrencyStrategy实现
        HystrixPlugins.reset();
        HystrixPlugins.getInstance().registerConcurrencyStrategy(this);
    }

    /*此方法应写入配置类中 - 上面的构造方法作用与此方法相同
    @PostConstruct
    public void init() {
        HystrixPlugins.getInstance().registerConcurrencyStrategy(new RequestAttributeHystrixConcurrencyStrategy());
    }*/

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        // 切换线程后，将父线程中上下文信息，记录到子线程任务
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return new WrappedCallable<>(callable, requestAttributes);
    }

    static class WrappedCallable<T> implements Callable<T> {

        private final Callable<T> target;
        private final RequestAttributes requestAttributes;

        public WrappedCallable(Callable<T> target, RequestAttributes requestAttributes) {
            this.target = target;
            this.requestAttributes = requestAttributes;
        }

        @Override
        public T call() throws Exception {
            try {
                // 切换线程后，将父线程中上下文信息，记录到子线程任务
                RequestContextHolder.setRequestAttributes(requestAttributes);
                return target.call();
            } finally {
                // 任务执行完成后，清空线程本地缓存
                RequestContextHolder.resetRequestAttributes();
            }
        }
    }

}
