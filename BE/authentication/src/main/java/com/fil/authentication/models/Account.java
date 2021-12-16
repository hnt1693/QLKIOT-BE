package com.fil.authentication.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fil.authentication.config.auditor.Auditor;
import com.fil.authentication.enums.ROLE_TYPE;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "auth_accounts",
        uniqueConstraints = {
                @UniqueConstraint(name = "Tên tài khoản constraint", columnNames = {"username"}),
                @UniqueConstraint(name = "Email constraint", columnNames = {"email"})})

public class Account extends Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(max = 50, min = 8, message = "Tên tài khoản có độ dài hợp lệ từ 8 - 50 ký tự")
    @Pattern(regexp = "^[1-9A-Za-z_\\-]+$", message = "Tên đăng nhập không chứa ký tự đặc biệt")
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Length(min = 8, message = "Mật khẩu có độ dài tối thiểu 8 ký tự")
    @Column(name = "password", nullable = false)
    private String password;
    @Email(message = "Email không hợp lệ")
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean actived;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userdetails_id", referencedColumnName = "id")
    private UserDetails userDetails;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "auth_groups_accounts",
            joinColumns = @JoinColumn(name = "accounts_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups = new HashSet<>();
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(cascade = CascadeType.MERGE)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", columnDefinition = "varchar(50) default 'USER'")
    private ROLE_TYPE roleType;

    @Column(name = "forgot_password")
    private String forgotPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActived() {
        return actived;
    }

    public void setActived(boolean actived) {
        this.actived = actived;
    }

    public com.fil.authentication.models.UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(com.fil.authentication.models.UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Set<com.fil.authentication.models.Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<com.fil.authentication.models.Group> groups) {
        this.groups = groups;
    }

    public com.fil.authentication.models.Customer getCustomer() {
        return customer;
    }

    public void setCustomer(com.fil.authentication.models.Customer customer) {
        this.customer = customer;
    }

    public ROLE_TYPE getRoleType() {
        return roleType;
    }

    public void setRoleType(ROLE_TYPE roleType) {
        this.roleType = roleType;
    }

    public String getForgotPassword() {
        return forgotPassword;
    }

    public void setForgotPassword(String forgotPassword) {
        this.forgotPassword = forgotPassword;
    }
}
