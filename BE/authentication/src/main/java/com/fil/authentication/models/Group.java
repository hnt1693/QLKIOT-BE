package com.fil.authentication.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fil.authentication.config.auditor.Auditor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "auth_groups",
        uniqueConstraints = {@UniqueConstraint(name = "Tên nhóm constraint", columnNames = {"name", "customer_id"})})
public class Group extends Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(min = 5, message = "Tên nhóm công ty ít nhất 10 ký tự")
    @Column(name = "name", nullable = false)
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "auth_groups_roles",
            joinColumns = @JoinColumn(name = "group_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
    Set<Role> roles = new HashSet<>();

    @ManyToMany(targetEntity = Account.class, cascade = {CascadeType.PERSIST})
    Set<Account> accounts = new HashSet<>();

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
