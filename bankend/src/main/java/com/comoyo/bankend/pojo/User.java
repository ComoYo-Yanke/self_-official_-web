package com.comoyo.bankend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id; // 用户id
    private String username; // 用户名
    private String password; // 密码
    private String name; // 昵称
    private String avatarURL; // 头像
    private String bio; // 个人简介
    private String location; // 位置
    private String email; // 邮箱
    private String github;
    private String bilibili;
    private Integer role; // 角色 1 管理员 2 普通用户 3 测试用户
    private LocalDateTime createdAt; // 创建时间
    private LocalDateTime updatedAt; // 最后更新时间
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", avatarURL='" + avatarURL + '\'' +
                ", bio='" + bio + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", github='" + github + '\'' +
                ", bilibili='" + bilibili + '\'' +
                ", role='" + role + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
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
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAvatarURL() {
        return avatarURL;
    }
    
    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
    
    public String getBio() {
        return bio;
    }
    
    public void setBio(String bio) {
        this.bio = bio;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getGithub() {
        return github;
    }
    
    public void setGithub(String github) {
        this.github = github;
    }
    
    public String getBilibili() {
        return bilibili;
    }
    
    public void setBilibili(String bilibili) {
        this.bilibili = bilibili;
    }
    
    public Integer getRole() {
        return role;
    }
    
    public void setRole(Integer role) {
        this.role = role;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
