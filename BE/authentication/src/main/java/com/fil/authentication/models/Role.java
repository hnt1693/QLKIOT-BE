package com.fil.authentication.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fil.authentication.commons.Utils;
import com.fil.authentication.config.auditor.Auditor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "auth_roles")
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private RoleMenuCase menuCase;
    @JsonIgnore
    @Column(name = "permission", nullable = false)
    private String permission = "[]";
    @Transient
    private List<String> permissions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<String> getPermissions() {
        try {
            return Utils.getObjectMapper().readValue(permission, List.class);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void setPermissions(List<String> permissions) {
        try {
            this.permission = Utils.getObjectMapper().writeValueAsString(permissions);
        } catch (Exception e) {
            this.permission = "[]";
        }
    }

    public List<String> getPermission() {
        try {
            return Utils.getObjectMapper().readValue(permission, List.class);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }


    public RoleMenuCase getMenuCase() {
        return menuCase;
    }

    public void setMenuCase(RoleMenuCase menuCase) {
        this.menuCase = menuCase;
    }
}
