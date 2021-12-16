package com.fil.authentication.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_client_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDetails {
    @Id
    @Column(name = "client_id")
    private String clientId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "resource_ids")
    private String resourceIds;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "client_secret")
    private String clientSecret;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "scope")
    private String scope;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "authorities")
    private String authorities;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "access_token_validity")
    private long accessTokenValidity;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "refresh_token_validity")
    private long refreshTokenValidity;
    @Column(name = "additional_information")
    private String additionalInformation;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "autoapprove")
    private long autoApprove;
    @Column(name = "client_name")
    private String clientName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "show", columnDefinition = "boolean default true")
    private boolean show;
    @Column(name = "icon")
    private String icon;

}
