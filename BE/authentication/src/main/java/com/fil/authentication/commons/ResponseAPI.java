package com.fil.authentication.commons;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
public class ResponseAPI {
    private int code;
    private String msg;
    private Object data;
    private Object errors;

    public ResponseAPI(String msg, Object data) {
        this.code = HttpStatus.OK.value();
        this.msg = msg;
        this.data = data;
    }

    public ResponseAPI(HttpStatus code, String msg, Object data) {
        this.code = code.value();
        this.msg = msg;
        this.data = data;
    }

    public ResponseAPI(HttpStatus code, String msg, Object data, Object errors) {
        this.code = code.value();
        this.msg = msg;
        this.data = data;
        this.errors = errors;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }
}

