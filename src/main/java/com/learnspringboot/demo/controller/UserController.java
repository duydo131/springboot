package com.learnspringboot.demo.controller;

import com.learnspringboot.demo.dto.UserInfoDTO;
import com.learnspringboot.demo.entity.User;
import com.learnspringboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<Page<?>> listUser(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {

        Sort sortable = Sort.by("id").ascending();

        if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        Pageable pageable = PageRequest.of(page, size, sortable);

        Page<User> users = userService.findUser(pageable);

        List<UserInfoDTO> userResponse = users.stream().map(User::mapToUserInfo).collect(Collectors.toList());
        return new ResponseEntity<>(new PageImpl<>(userResponse), HttpStatus.OK);
    }
}
