package com.server.sso.pojo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Userinfo {
    private String unionId;

    @NotNull(message = "用户名不得为空", groups = {Insert.class, Select.class})
    private String username;

    @NotNull(message = "密码不得为空", groups = {Insert.class, Select.class})
    private String password;

    private Date created;

    private Integer status;

    private String phone;

    @Email(message = "邮件格式错误", groups = Insert.class)
    private String email;

    private Integer gender;

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
