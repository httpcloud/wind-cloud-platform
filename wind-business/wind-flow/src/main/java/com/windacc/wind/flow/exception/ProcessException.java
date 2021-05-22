package com.windacc.wind.flow.exception;

import com.windacc.wind.toolkit.enums.CodeEnum;
import com.windacc.wind.toolkit.exception.BaseException;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/20
 */
public class ProcessException extends BaseException {

    private static final long serialVersionUID = -4531286912417346083L;

    public ProcessException(String message) {
        super(CodeEnum.PROCESS_ERROR, message);
    }
}
