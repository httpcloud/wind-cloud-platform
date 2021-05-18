package com.windacc.wind.gateway.properties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidateCodeProperties {
    /**
     * 设置认证通时不需要验证码的clientId
     */
    private String[] ignoreClientCode = {};
}
