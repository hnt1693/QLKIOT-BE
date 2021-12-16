package com.fil.coffee.commons;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
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
}

