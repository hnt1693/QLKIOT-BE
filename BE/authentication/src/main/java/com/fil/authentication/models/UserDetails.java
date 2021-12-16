package com.fil.authentication.models;

import com.fil.authentication.config.auditor.Auditor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NegativeOrZero;

@Entity
@Table(name = "auth_userdetails")
@Data
@NoArgsConstructor
@Where(clause = " daXoa is false ")
@SQLDelete(sql = " set daXoa is true ")
public class UserDetails extends Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 5, message = "Tên có độ dài tối thiểu là 5")
    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tinhfkma", referencedColumnName = "code")
    private DanhMucDiaBan tinhThanh;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "huyenfkma", referencedColumnName = "code")
    private DanhMucDiaBan quanHuyen;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "xafkma", referencedColumnName = "code")
    private DanhMucDiaBan xaPhuong;


    @Column(name = "active", columnDefinition = "boolean default false")
    private boolean isActived;
}
