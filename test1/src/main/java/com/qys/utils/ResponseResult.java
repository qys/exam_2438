package com.qys.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qys.enums.AppHttpCodeEnum;

import java.io.Serializable;

/**
 * @author qys
 * @description
 * @create 2022-09-28-14:59
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable {

    private Integer code;

    private String errMsg;

    private T result;

    public ResponseResult() {
        this.code = AppHttpCodeEnum.SUCCESS.getCode();
        this.errMsg = AppHttpCodeEnum.SUCCESS.getMsg();
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.result = data;
    }

    public ResponseResult(Integer code, String errMsg, T data) {
        this.code = code;
        this.errMsg = errMsg;
        this.result = data;
    }

    public ResponseResult(Integer code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    public static ResponseResult errorResult(int code, String msg) {
        ResponseResult result = new ResponseResult();
        return result.error(code, msg);
    }

    public static ResponseResult okResult() {
        ResponseResult result = new ResponseResult();
        return result;
    }

    public static ResponseResult okResult(int code, String msg) {
        ResponseResult result = new ResponseResult();
        return result.ok(code, null, msg);
    }

    public static ResponseResult okResult(Object data) {
        ResponseResult result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS, AppHttpCodeEnum.SUCCESS.getMsg());
        if (data != null) {
            result.setResult(data);
        }
        return result;
    }

    public static ResponseResult errorResult(AppHttpCodeEnum enums) {
        return setAppHttpCodeEnum(enums, enums.getMsg());
    }

    public static ResponseResult errorResult(AppHttpCodeEnum enums, String msg) {
        return setAppHttpCodeEnum(enums, msg);
    }

    public static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums) {
        return okResult(enums.getCode(), enums.getMsg());
    }

    public static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums, String msg) {
        return okResult(enums.getCode(), msg);
    }

    public ResponseResult error(Integer code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
        return this;
    }

    public ResponseResult ok(Integer code, T result) {
        this.code = code;
        this.result = result;
        return this;
    }

    public ResponseResult ok(Integer code, T result, String errMsg) {
        this.code = code;
        this.result = result;
        this.errMsg = errMsg;
        return this;
    }

    public ResponseResult ok(T result) {
        this.result = result;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
