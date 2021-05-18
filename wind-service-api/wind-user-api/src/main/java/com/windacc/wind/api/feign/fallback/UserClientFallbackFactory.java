package com.windacc.wind.api.feign.fallback;

import com.windacc.wind.api.feign.IUserClient;
import com.windacc.wind.toolkit.entity.LoginUser;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 17:05
 */
@Slf4j
public class UserClientFallbackFactory implements FallbackFactory<IUserClient> {

    @Override
    public IUserClient create(Throwable cause) {

        return new IUserClient() {

            @Override
            public LoginUser findByUsername(String username) {
                log.error("通过用户名查询用户异常:{}.", username, cause);
                return null;
            }

        };
    }
}
