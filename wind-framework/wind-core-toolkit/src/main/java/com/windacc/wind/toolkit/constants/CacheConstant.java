package com.windacc.wind.toolkit.constants;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 17:05
 */
public interface CacheConstant {
    /**
     * 用户信息分隔符
     */
    String CLIENT_KEY = "oauth_client_details:client_id";

    /**
     * 用户信息头
     */
    String CACHE_CLIENT_KEY = "cache:" + CLIENT_KEY;

    /**
     * 用户信息分隔符
     */
    String CLIENT_DETAILS = "BaseClientDetails:clientId";

    /**
     * 用户信息头
     */
    String CACHE_CLIENT_DETAILS = "cache:" + CLIENT_DETAILS;

}
