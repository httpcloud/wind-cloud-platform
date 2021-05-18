package com.windacc.wind.toolkit.exception;

import com.windacc.wind.toolkit.enums.CodeEnum;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/16
 */
public class SignException extends BaseException {

    private static final long serialVersionUID = 8258772995806050997L;

    public SignException(String message) {
        super(CodeEnum.SIGN_ERROR, message);
    }

    public SignException(Throwable cause) {
        super(CodeEnum.SIGN_ERROR, cause);
    }

}
