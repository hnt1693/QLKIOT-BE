package com.fil.authentication.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fil.authentication.models.UserDetails;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class AccountPayload {
    @Length(max = 50, min = 8, message = "Tên tài khoản có độ dài hợp lệ từ 8 - 50 ký tự")
    @Pattern(regexp = "^[1-9A-Za-z_\\-]+$", message = "Tên đăng nhập không chứa ký tự đặc biệt")
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Length(min = 8, message = "Mật khẩu có độ dài tối thiểu 8 ký tự")
    private String password;
    @Email(message = "Email không hợp lệ")
    private String email;
    private boolean actived;
    private UserDetails userDetails;
}
