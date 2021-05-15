package com.windacc.wind.tool.exception;

import com.windacc.wind.tool.enums.CodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:26
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 7106888864969266482L;

	@Getter
	private final CodeEnum codeEnum;

	public BaseException(String message) {
		super(message);
		this.codeEnum = CodeEnum.FAILURE;
	}

	public BaseException(CodeEnum codeEnum){
		super(codeEnum.getMessage());
		this.codeEnum = codeEnum;
	}

	public BaseException(CodeEnum codeEnum, String message){
		super(codeEnum.getMessage() + ":" + message);
		this.codeEnum = codeEnum;
	}

	public BaseException(CodeEnum codeEnum, Throwable cause) {
		super(cause);
		this.codeEnum = codeEnum;
	}

	public BaseException(Throwable cause) {
		super(cause);
		this.codeEnum = CodeEnum.FAILURE;
	}

	public HttpStatus getHttpStatus() {
		return codeEnum.getHttpStatus();
	}

	public String getCode() {
		return codeEnum.getCode();
	}
}
