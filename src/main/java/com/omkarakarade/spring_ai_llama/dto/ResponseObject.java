package com.omkarakarade.spring_ai_llama.dto;

public class ResponseObject {
    private Object data;

    public ResponseObject(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
