package com.fil.authentication.config.auditor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fil.authentication.constants.Constants;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"daXoa", "ngaySua", "nguoiSua"})
public class Auditor implements Serializable {
    @CreatedBy
    @Column(name = "nguoitao", updatable = false)
    private String nguoiTao;

    @CreatedDate
    @Column(name = "ngaytao", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = Constants.DATETIME_FORMAT)
    private Date ngayTao;

    @LastModifiedBy
    @Column(name = "nguoisua")
    private String nguoiSua;

    @LastModifiedDate
    @Column(name = "ngaysua")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = Constants.DATETIME_FORMAT)
    private Date ngaySua;

    @Column(name = "daxoa", columnDefinition = "boolean default false")
    private boolean daXoa;

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNguoiSua() {
        return nguoiSua;
    }

    public void setNguoiSua(String nguoiSua) {
        this.nguoiSua = nguoiSua;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }
}
