package com.windacc.wind.flow.error;

import com.windacc.wind.toolkit.entity.Result;
import com.windacc.wind.toolkit.enums.CodeEnum;
import com.windacc.wind.toolkit.error.BaseExceptionAdvice;
import org.flowable.common.engine.api.FlowableException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/18
 */
@ControllerAdvice
public class FlowExceptionAdvise extends BaseExceptionAdvice {

    @ResponseBody
    @ExceptionHandler({ FlowableException.class })
    public ResponseEntity<Result<?>> handleError(FlowableException e) {

        return exceptionHandle(CodeEnum.BUSINESS_ERROR, e);
    }

}
