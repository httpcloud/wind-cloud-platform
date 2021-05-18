package com.windacc.wind.toolkit.constants;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/14 23:31
 */
public interface SecurityConstant {

    /**
     * 用户状态-锁定
     */
    String USER_STATUS_OK = "0";
    /**
     * 用户状态-锁定
     */
    String USER_STATUS_EXPIRED = "1";
    /**
     * 用户状态-锁定
     */
    String USER_STATUS_LOCK = "2";
    /**
     * 用户状态-锁定
     */
    String USER_STATUS_ENABLED = "3";

    String LOGIN_PROCESSING_URL = "/user/login";

    String LOGIN_PAGE = "/login.html";

}
