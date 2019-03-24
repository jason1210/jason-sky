package com.jason.sky.vo;

import java.io.Serializable;

/**
 * @author: jason
 * @Date: 2019-03-24
 */
public class Result implements Serializable {
    
    public static final Integer SUCCESS_CODE            = 200;
    public static final Integer VALIDATE_ERROR_CODE     = 400;
    public static final Integer FAILED_ERROR_CODE       = 500;
    /**
     * 调用状态
     */
    private Integer code;

    /**
     * 错误信息
     */
    private Object message;

    /**
     * 结果
     */
    private Object data;

    public Result() {
    }

    public Result(Integer code, Object message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, Object data, Object message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static Result success(Object data) {
        Result r = new Result();
        r.setCode(Result.SUCCESS_CODE);
        r.setData(data);
        return r;
    }

    public static Result sysError(Object messageData) {
        Result r = new Result();
        r.setCode(Result.FAILED_ERROR_CODE);
        r.setMessage(messageData);
        return r;
    }

    public static Result validateError(Object validateData) {
        Result r = new Result();
        r.setCode(Result.VALIDATE_ERROR_CODE);
        r.setMessage(validateData);
        return r;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
