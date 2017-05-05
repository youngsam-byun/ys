package com.ys.app.model;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import java.util.Date;

//@FieldMatch.List({ @FieldMatch(first = "password", second = "passwordConfirm") })
public class User {

    private static final String DEFAULT_PROVIDER="APP";

    private String providerId;
    private String connectionId;
    private String providerConnectionId;
    private int rank;
    private String displayName;
    private String profileUrl;
    private String imageUrl;
    private String accessToken;
    private String secret;
    private String refreshToken;
    private Long expireTime;

    private int id;

    @NotEmpty
    @Email
    //@IsUnique(fieldName = "user_email")
    private String email;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String passwordConfirm;


    @Min(1)
    private int roleId;

    private String str;
    private boolean enabled;
    private boolean notLocked;
    private Date createTime;
    private Date updateTime;
    private int trial;

    public User(){
        providerId=DEFAULT_PROVIDER;
        connectionId= RandomStringUtils.randomAlphanumeric(10);
        providerConnectionId =DEFAULT_PROVIDER+connectionId;
        roleId=Role.USER.getId();
        createTime=new Date();
        updateTime=new Date();
        trial=0;
        str=RandomStringUtils.randomAlphabetic(10);
        enabled=true;
    }


    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    public String getProviderConnectionid() {
        return providerConnectionId;
    }

    public void setProviderConnectionid(String providerconnectionid) {
        this.providerConnectionId = providerconnectionid;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isNotLocked() {
        return notLocked;
    }

    public void setNotLocked(boolean notLocked) {
        this.notLocked = notLocked;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getTrial() {
        return trial;
    }

    public void setTrial(int trial) {
        this.trial = trial;
    }
}
