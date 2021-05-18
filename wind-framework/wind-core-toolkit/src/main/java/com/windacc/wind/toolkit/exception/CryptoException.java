package com.windacc.wind.toolkit.exception;

import com.windacc.wind.toolkit.enums.CodeEnum;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/16
 */
public class CryptoException extends BaseException {

    private static final long serialVersionUID = -4500244985881368070L;

    public CryptoException(String message) {
        super(CodeEnum.CRYPTO_ERROR, message);
    }

    public CryptoException(Throwable cause) {
        super(CodeEnum.CRYPTO_ERROR, cause);
    }

}
