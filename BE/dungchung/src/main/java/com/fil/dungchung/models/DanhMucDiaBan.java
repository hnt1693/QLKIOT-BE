package com.fil.dungchung.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "danhmuc_diaban")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DanhMucDiaBan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "parent_code")
    private String parentCode;
}
