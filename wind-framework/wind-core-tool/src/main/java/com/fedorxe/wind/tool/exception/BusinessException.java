package com.fedorxe.wind.tool.exception;

import com.fedorxe.wind.tool.enums.CodeEnum;

/**
 * <p>Description desc   </p>
 *
 * @author fedorxe
 * @date 2021/5/15 10:26
 */
public class BusinessException extends BaseException {

    private static final long serialVersionUID = -1076100586506569598L;

    public BusinessException(String message) {
        super(CodeEnum.BUSINESS_ERROR, message);
    }

    public BusinessException(Throwable cause) {
        super(CodeEnum.BUSINESS_ERROR, cause);
    }

}
