package com.tecsup.financego.controller;

import com.tecsup.financego.common.type.ApiResponse;
import com.tecsup.financego.common.type.UserDto;
import com.tecsup.financego.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping("/register")
    public ApiResponse<UserDto> register(@RequestBody UserDto userDto) {
        return new ApiResponse<UserDto>().toSuccess(userService.register(userDto));
    }

    @GetMapping("/{id}")
    public ApiResponse<UserDto> getUser(@PathVariable Long id) {
        return new ApiResponse<UserDto>().toSuccess(userService.getUser(id));
    }

    @GetMapping("/me")
    public ApiResponse<UserDto> getMe(Principal principal) {
        return new ApiResponse<UserDto>().toSuccess(userService.getMe(principal));
    }
}


