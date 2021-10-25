package com.learnspringboot.demo.dto;

import com.learnspringboot.demo.entity.User;

public class UserInfoDTO {
    private String username;
    private String email;

    public UserInfoDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
