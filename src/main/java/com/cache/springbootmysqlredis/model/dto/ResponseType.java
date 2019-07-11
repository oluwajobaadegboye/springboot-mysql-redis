package com.cache.springbootmysqlredis.model.dto;

public enum ResponseType {
    SUCCESSFUL("00","Successful"),
    CUSTOMER_EXIST("01","Customer Exist"),
    CUSTOMER_DOES_NOT_FOUND("404","Customer does not exist");

    private String responseCode;
    private String responseMessage;

    ResponseType(String responseCode,String responseMessage){
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
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
}
