package br.com.alura.walletapi.application.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import br.com.alura.walletapi.application.dtos.UserFormDto;
import br.com.alura.walletapi.application.dtos.UserResponseDto;
import br.com.alura.walletapi.services.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public void createUser(@RequestBody UserFormDto user) {
        userService.createUser(user);
    }

}
