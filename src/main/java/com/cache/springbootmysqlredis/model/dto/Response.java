package com.cache.springbootmysqlredis.model.dto;

import java.io.Serializable;
import java.util.List;

public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String responseCode;
    private String responseMessage;
    private T data;
    private List<T> list;

    public Response() {
    }

    public Response(String responseCode, String responseMessage, T data, List<T> list) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = data;
        this.list = list;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
