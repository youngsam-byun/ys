package com.ys.app.exception;

/**
 * Created by byun.ys on 4/19/2017.
 */

public class CustomException extends RuntimeException {

    private Class tClass;
    private String method;
    private String message;
    private Throwable throwable;

    public CustomException(Class tClass,String method,String messsage,Throwable throwable ){
        this.tClass=tClass;
        this.method=method;
        this.message=messsage;
        this.throwable=throwable;
    }

    public Class gettClass() {
        return tClass;
    }

    public void settClass(Class tClass) {
        this.tClass = tClass;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
