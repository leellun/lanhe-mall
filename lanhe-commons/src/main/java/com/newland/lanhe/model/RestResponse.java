

package com.newland.lanhe.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(Include.NON_NULL)
@ApiModel(value = "RestResponse<T>", description = "响应通用参数包装")
public class RestResponse<T> {

    @ApiModelProperty("响应错误编码,200为正常")
    private int code;

    @ApiModelProperty("响应错误信息")
    private String msg;

    @ApiModelProperty("响应内容")
    private T result;

    public static <T> RestResponse<T> success() {
        RestResponse<T> response = new RestResponse<T>();
        response.setCode(200);
        return response;
    }

    public static <T> RestResponse<T> success(T result) {
        RestResponse<T> response = new RestResponse<T>();
        response.setCode(200);
        response.setResult(result);
        return response;
    }

    public static <T> RestResponse<T> error(String msg) {
        RestResponse<T> response = new RestResponse<T>();
        response.setCode(-2);
        response.setMsg(msg);
        return response;
    }

    public static <T> RestResponse<T> error(int code, String msg) {
        RestResponse<T> response = new RestResponse<T>();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

}
