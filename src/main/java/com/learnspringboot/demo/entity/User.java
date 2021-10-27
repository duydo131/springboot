package com.learnspringboot.demo.entity;

import com.learnspringboot.demo.dto.UserInfoDTO;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String username;

    @Column
    @NotNull
    private String password;

    @Column
    @NotNull
    private String email;

    @Column
    @NotNull
    private boolean active;
}
