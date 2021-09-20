package br.com.alura.walletapi.application.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import br.com.alura.walletapi.application.dtos.UserFormDto;
import br.com.alura.walletapi.application.dtos.UserResponseDto;
import br.com.alura.walletapi.domain.services.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<UserResponseDto> getUsers(@PageableDefault(size = 10) Pageable pagination) {
        return userService.getUsers(pagination);
    }

    @PostMapping
    public void createUser(@RequestBody UserFormDto user) {
        userService.createUser(user);
    }

}
