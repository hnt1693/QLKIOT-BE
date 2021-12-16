package com.fil.authentication.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPayload {
    @NotNull(message = "Tên khách hàng không được trống")
    @Length(min = 5, message = "Tên khách hàng tối thiểu 5 ký tự")
    private String customerName;

    @NotNull(message = "Số điện thoại không được trống")
    private String phone;
    private String address;

    @Length(max = 50, min = 8, message = "Tên tài khoản có độ dài hợp lệ từ 8 - 50 ký tự")
    @Pattern(regexp = "^[1-9A-Za-z_\\-]+$", message = "Tên đăng nhập không chứa ký tự đặc biệt")
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Pattern(regexp = "^[1-9A-Za-z_\\-@!#$%^&*()]+$", message = "Mật khẩu không chứa ký tự đặc biệt")
    @Length(min = 8, message = "Mật khẩu có độ dài tối thiểu 8 ký tự")
    private String password;

    @Email(message = "Email không hợp lệ")
    private String email;

}
