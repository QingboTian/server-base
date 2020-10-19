package com.server.exception;

/**
* @author tianqingbo3
* @version 1.0
* @date 2020/10/15 16:48
*/
public class AppException extends Exception {

    private String message;
    private Integer code;

    public AppException() {
        super();
    }

    public AppException(String message, Integer code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public AppException(String message, Integer code, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.code = code;
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    protected AppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
