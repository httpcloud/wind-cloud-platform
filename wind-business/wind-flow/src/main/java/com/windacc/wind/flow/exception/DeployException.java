package com.windacc.wind.flow.exception;

import com.windacc.wind.toolkit.enums.CodeEnum;
import com.windacc.wind.toolkit.exception.BaseException;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/20
 */
public class DeployException extends BaseException {

    private static final long serialVersionUID = -4531286912417346083L;

    public DeployException(String message) {
        super(CodeEnum.DEPLOY_ERROR, message);
    }
}
