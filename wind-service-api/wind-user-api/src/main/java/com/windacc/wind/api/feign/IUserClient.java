package com.windacc.wind.api.feign;

import com.windacc.wind.api.feign.fallback.UserClientFallbackFactory;
import com.windacc.wind.toolkit.constants.ServiceConstant;
import com.windacc.wind.toolkit.entity.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 17:05
 */
@FeignClient(value = ServiceConstant.USER_SERVICE, fallbackFactory = UserClientFallbackFactory.class)
public interface IUserClient {

    @GetMapping("/feign/users-anon/findByUsername")
    LoginUser findByUsername(@RequestParam("username") String username);


}
