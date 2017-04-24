package com.ys.app.exception;

/**
 * Created by byun.ys on 4/19/2017.
 */

public class CustomException extends RuntimeException {

    private Class tClass;
    private String method;
    private String message;

    public CustomException(Class tClass,String method,String messsage ){
        this.tClass=tClass;
        this.method=method;
        this.message=messsage;
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

}
