package com.gulbagomedovich.brs.controller.v1.api;

import com.gulbagomedovich.brs.dto.UserDto;
import com.gulbagomedovich.brs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public UserDto signup(UserDto userDto) {
        return userService.signup(userDto.setAdmin(false));
    }

}
