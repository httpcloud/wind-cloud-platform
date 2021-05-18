package com.windacc.wind.toolkit.exception;

import com.windacc.wind.toolkit.enums.CodeEnum;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:26
 */
public class BusinessException extends BaseException {

    private static final long serialVersionUID = -6828823618224308733L;

    public BusinessException(String message) {
        super(CodeEnum.BUSINESS_ERROR, message);
    }

    public BusinessException(Throwable cause) {
        super(CodeEnum.BUSINESS_ERROR, cause);
    }

}
