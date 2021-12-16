package com.fil.authentication.enums;

public enum JOB_STATUS {
    RUNNING("RUNNING"),PAUSE("PAUSE"),FINISH("FINISH");
    String value;
    JOB_STATUS(String value) {
        this.value = value;
    }
}
