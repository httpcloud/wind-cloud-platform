package com.windacc.wind.toolkit.error;

import com.windacc.wind.toolkit.entity.Result;
import com.windacc.wind.toolkit.enums.CodeEnum;
import com.windacc.wind.toolkit.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/16
 */
@Slf4j
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class BaseExceptionAdvice {

	@ResponseBody
	@ExceptionHandler({ BaseException.class })
	public ResponseEntity<Result<?>> handleError(BaseException e) {

		return exceptionHandle(e.getCodeEnum(), e);
	}

	@ResponseBody
	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<Result<?>> handleError(IllegalArgumentException e) {

		return exceptionHandle(CodeEnum.BAD_REQUEST, e);
	}

	@ResponseBody
	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Result<?>> handleError(AccessDeniedException e) {

		return exceptionHandle(CodeEnum.FORBIDDEN, e);
	}

	@ResponseBody
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Result<?>> handleError(MissingServletRequestParameterException e) {

		return exceptionHandle(CodeEnum.BAD_REQUEST, e);
	}

	@ResponseBody
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Result<?>> handleError(MethodArgumentTypeMismatchException e) {

		return exceptionHandle(CodeEnum.BAD_REQUEST, e);
	}

	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Result<?>> handleError(MethodArgumentNotValidException e) {

		return exceptionHandle(CodeEnum.BAD_REQUEST, e);
	}

	@ResponseBody
	@ExceptionHandler(BindException.class)
	public ResponseEntity<Result<?>> handleError(BindException e) {

		return exceptionHandle(CodeEnum.BAD_REQUEST, e);
	}

	@ResponseBody
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<Result<?>> handleError(NoHandlerFoundException e) {

		return exceptionHandle(CodeEnum.NOT_FOUND, e);
	}

	@ResponseBody
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Result<?>> handleError(HttpRequestMethodNotSupportedException e) {

		return exceptionHandle(CodeEnum.METHOD_NOT_ALLOWED, e);
	}

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<Result<?>> handleError(InsufficientAuthenticationException e) {

        throw e;
    }

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Result<?>> handleError(Exception e) {

		return exceptionHandle(CodeEnum.INTERNAL_SERVER_ERROR, e);
	}

	protected ResponseEntity<Result<?>> exceptionHandle(CodeEnum codeEnum, Exception e) {

		log.error("handle error:code={},msg={}.", codeEnum.getCode(), e.getMessage(), e);
		Result<?> result = Result.failed(codeEnum.getCode(), e.getMessage());
		HttpStatus status = codeEnum.getHttpStatus();
		return new ResponseEntity<>(result, status);
	}


}
