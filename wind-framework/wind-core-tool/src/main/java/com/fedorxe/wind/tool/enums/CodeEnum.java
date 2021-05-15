package com.fedorxe.wind.tool.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>Description desc   </p>
 *
 * @author fedorxe
 * @date 2021/5/15 10:26
 */
@Getter
@AllArgsConstructor
public enum CodeEnum {

    /**
     * 成功
     */
    SUCCESS("0", HttpStatus.OK, "success", ""),

    /**
     * 系统异常
     */
    FAILURE("8000", HttpStatus.OK, "系统异常", ""),
    /**
     * 业务异常
     */
    BUSINESS_ERROR("8001", HttpStatus.OK, "业务异常", ""),
    /**
     * 服务繁忙请稍后重试
     */
    UN_REACHABLE("8002", HttpStatus.OK, "服务繁忙请稍后重试", "服务繁忙请稍后重试"),
    /**
     * 分布式锁异常
     */
    UNLOCK("8003", HttpStatus.OK, "分布式锁异常", "分布式锁异常"),
    /**
     * 数据加解密异常
     */
    CRYPTO_ERROR("8004", HttpStatus.OK, "数据加解密异常", "数据加解密异常"),
    /**
     * 流量限制
     */
    RATE_LIMIT("8005", HttpStatus.OK, "流量限制", "流量限制"),
    /**
     * Excel处理异常
     */
    EXCEL_ERROR("8006", HttpStatus.OK, "Excel处理异常", "Excel处理异常"),
    /**
     * 没有权限请求当前方法
     */
    DATA_ACCESS("8007", HttpStatus.OK, "没有权限请求当前方法", "没有权限请求当前方法"),
    /**
     * 没有权限请求当前方法
     */
    SIGN_ERROR("8008", HttpStatus.OK, "接口验签失败!", "接口验签失败!"),
    /**
     * 没有权限请求当前方法
     */
    BAD_CLIENT_CREDENTIALS("8009", HttpStatus.OK, "客户端凭证错误!", "客户端凭证错误!"),


    /**
     * 错误的请求
     */
    BAD_REQUEST("400", HttpStatus.BAD_REQUEST, "错误的请求", "Bad Request:"),
    /**
     * 未经授权
     */
    UNAUTHORIZED("401", HttpStatus.UNAUTHORIZED, "未经授权", "Unauthorized"),
    /**
     * 访问被禁止
     */
    FORBIDDEN("403", HttpStatus.FORBIDDEN, "访问被禁止", "Forbidden"),
    /**
     * 没有找到该资源
     */
    NOT_FOUND("404", HttpStatus.NOT_FOUND, "没有找到该资源", "Not Found"),
    /**
     * 方法不允许
     */
    METHOD_NOT_ALLOWED("405", HttpStatus.METHOD_NOT_ALLOWED, "方法不允许", "Method Not Allowed"),
    /**
     * 请求超时
     */
    REQUEST_TIMEOUT("405", HttpStatus.REQUEST_TIMEOUT, "请求超时", "Request Timeout"),
    /**
     * 请求超时
     */
    INTERNAL_SERVER_ERROR("500", HttpStatus.INTERNAL_SERVER_ERROR, "内部服务器错误", "Internal Server Error")
    ;


    /**
     * 错误返回码
     */
    final private String code;

    /**
     * 返回的http status
     */
    final private HttpStatus httpStatus;

    /**
     * 返回用户界面提示
     */
    final private String message;

    /**
     * 返回错误信息
     */
    final private String note;
}
