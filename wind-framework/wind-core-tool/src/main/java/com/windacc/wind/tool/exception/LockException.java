package com.windacc.wind.tool.exception;

import com.windacc.wind.tool.enums.CodeEnum;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:27
 */
public class LockException extends BaseException {
    private static final long serialVersionUID = 6610083281801529147L;

    public LockException(String message) {
        super(CodeEnum.UNLOCK, message);
    }
}
