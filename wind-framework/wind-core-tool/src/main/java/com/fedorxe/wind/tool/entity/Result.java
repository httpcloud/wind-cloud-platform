package com.fedorxe.wind.tool.entity;

import com.fedorxe.wind.tool.constants.StringPool;
import com.fedorxe.wind.tool.enums.CodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * <p>Description desc   </p>
 *
 * @author fedorxe
 * @date 2021/5/15 10:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "返回信息")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 7540207494016275047L;

    @ApiModelProperty(value = "承载数据")
    private T data;

    private Meta meta;

    public static <T> Result<T> of(T data) {
        return of(data, CodeEnum.SUCCESS.getCode(), StringPool.SUCCESS);
    }

    //public static <T> Result<T> succeed(T model, String msg) {
    //    return succeedWith(model, CodeEnum.SUCCESS.getCode(), msg);
    //}
    //
    public static <T> Result<T> of(T data, String code, String message) {
        return new Result<>(data, new Meta(code, message, StringPool.EMPTY));
    }
    //
    //public static <T> Result<T> failed(String msg) {
    //    return failed(null, CodeEnum.FAILURE.getCode(), msg);
    //}
    //
    //public static <T> Result<T> failed(CodeEnum codeEnum) {
    //    return failed(null, codeEnum.getCode(), codeEnum.getMsg());
    //}
    //
    //public static <T> Result<T> failed(CodeEnum codeEnum, String msg) {
    //    return failed(null, codeEnum.getCode(), codeEnum.getMsg() + ":" + msg);
    //}
    //
    //public static <T> Result<T> failed(Integer code, String msg) {
    //    return failed(null, code, msg);
    //}
    //
    //public static <T> Result<T> failed(T data, Integer code, String msg) {
    //    return new Result<>(data, code, msg);
    //}

    @Setter
    @Getter
    @AllArgsConstructor
    private static class Meta implements Serializable {

        private static final long serialVersionUID = 7665647606894659130L;

        private String code;

        private String message;

        private String note;
    }

}
