package com.windacc.wind.toolkit.exception;

import com.windacc.wind.toolkit.enums.CodeEnum;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:27
 */
public class LockException extends BaseException {

    private static final long serialVersionUID = -4389816845595386749L;

    public LockException(String message) {
        super(CodeEnum.UNLOCK, message);
    }
}
