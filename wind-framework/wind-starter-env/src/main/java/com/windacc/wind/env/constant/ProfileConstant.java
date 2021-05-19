package com.windacc.wind.env.constant;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:24
 */
public interface ProfileConstant {

    /**
     * 用户id信息头
     */
    String ACTIVE_DEV = "dev";

    String ACTIVE_TEST = "test";

    String ACTIVE_PROD = "prod";

    String COMMON_PROFILE = "wind";

    String CLOUD_PROFILE = "cloud";

    List<String> LOADING_PROFILE = Arrays.asList(
        CLOUD_PROFILE,
        COMMON_PROFILE
    );

}
