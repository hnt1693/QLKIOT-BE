package com.fil.authentication.payload.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AccountResetPWPayload {

    @Length(max = 50, min = 8, message = "Tên tài khoản có độ dài hợp lệ từ 8 - 50 ký tự")
    @Pattern(regexp = "^[1-9A-Za-z_\\-]+$", message = "Tên đăng nhập không chứa ký tự đặc biệt")
    private String username;

    @Pattern(regexp = "^[1-9A-Za-z_\\-@!#$%^&*()]+$", message = "Mật khẩu không chứa ký tự đặc biệt")
    @Length(min = 8, message = "Mật khẩu có độ dài tối thiểu 8 ký tự")
    private String password;
}
