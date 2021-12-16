package com.fil.authentication.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "auth_menu")
@AllArgsConstructor
@NoArgsConstructor
public class MenuCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    @NotNull
    @Column(name = "url", nullable = false)
    private String url;
    @NotNull
    @Column(name = "regex", nullable = false)
    private String regex;
    @NotNull
    @Column(name = "icon", nullable = false)
    private String icon;
    @NotNull
    @Column(name = "activated", nullable = false, columnDefinition = "boolean default true")
    private boolean isActivated;

    @Column(name = "parent_id")
    private Long parentId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Set<MenuCase> childMenus = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private ClientDetails clientDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Set<MenuCase> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(Set<MenuCase> childMenus) {
        this.childMenus = childMenus;
    }

    public com.fil.authentication.models.ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(com.fil.authentication.models.ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
