package com.fil.authentication.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fil.authentication.config.auditor.Auditor;
import com.fil.authentication.constants.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "auth_customers", uniqueConstraints = {@UniqueConstraint(name = "company constraint", columnNames = "name")})
@Data
@NoArgsConstructor
@Where(clause = "daXoa is false")
@SQLDelete(sql = "set daXoa is true")
public class Customer extends Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Tên khách hàng không được trống")
    @Length(min = 10, message = "Tên khách hàng ít nhất 10 ký tự")
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "address", columnDefinition = "text")
    private String address;
    @NotNull(message = "Số điện thoại không được trống")
    @Column(name = "phone", columnDefinition = "text")
    private String phoneNumber;
    @Transient
    private Account account;
    @Column(name = "activated", columnDefinition = "boolean default true")
    private boolean isActivated;
    @JsonFormat(pattern = Constants.DATETIME_FORMAT)
    @Column(name = "expired_time")
    private LocalDateTime expiredTime;

}
