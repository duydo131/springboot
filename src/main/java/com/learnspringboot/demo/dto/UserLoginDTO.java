package com.learnspringboot.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class UserLoginDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;
}

