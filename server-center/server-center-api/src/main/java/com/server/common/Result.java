package com.server.common;

import lombok.Data;

@Data
public class Result<T> {
    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private T data;

    //构建其他状态的result对象
    public static <T> Result<T> build(Integer status, String msg, T data) {
        return new Result(status, msg, data);
    }

    public static <T> Result<T> ok(T data) {
        return new Result(data);
    }

    public static Result ok() {
        return new Result(null);
    }

    public Result() {

    }

    public static Result build(Integer status, String msg) {
        return new Result(status, msg, null);
    }

    public Result(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Result(T data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }
}
