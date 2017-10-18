package com.jspunion.beans;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class HttpEntity<E> {

    private Integer status;

    private String reason;

    private String message;

    private E data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public static HttpEntity fromResponseStatus(ResponseStatus status) {
        HttpEntity entity = new HttpEntity();
        HttpStatus code = status.code();
        entity.status = code.value();
        entity.reason = code.getReasonPhrase();
        entity.message = status.reason();
        return entity;
    }

    public  static <T> HttpEntity ok(T t){
        HttpEntity<T> entity = new HttpEntity();
        HttpStatus ok = HttpStatus.OK;
        entity.status = ok.value();
        entity.reason = ok.getReasonPhrase();
        entity.message = "请求成功";
        entity.setData(t);
        return entity;
    }

    public  static HttpEntity error(){
        HttpEntity entity = new HttpEntity();
        HttpStatus error = HttpStatus.INTERNAL_SERVER_ERROR;
        entity.status = error.value();
        entity.reason = error.getReasonPhrase();
        entity.message = "服务器错误";
        return entity;
    }
}
