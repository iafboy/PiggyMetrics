package com.cncustompoc.license.common;

import java.io.Serializable;

public class JsonResult<T>  implements Serializable {
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    private int state=0;
    private String message = "";
    private T data=null;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public JsonResult() {
        state = SUCCESS;
    }

    public JsonResult(int state,String message) {
        this.state = state;
        this.message = message;
    }

    //为了方便,重载n个构造器
    public JsonResult(int state, T data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(T data) {

        this.data = data;
    }

    //为了方便,重载n个构造器
    public JsonResult(int state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }
}