package com.learnspringboot.demo.entity;

import com.learnspringboot.demo.dto.UserInfoDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private boolean active;

    public UserInfoDTO mapToUserInfo(){
        return new UserInfoDTO(username, email);
    }
}
