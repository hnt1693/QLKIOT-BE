package com.fil.authentication.enums;

public enum ROLE_TYPE {
    SUPER_ADMIN("SUPER_ADMIN"), ADMIN("ADMIN"), USER("USER"), EMPLOYEE("EMPLOYEE");
    String value;

    ROLE_TYPE(String value) {
        this.value = value;
    }
}
