package br.com.alura.walletapi.application.controllers;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserFormDto userFormDto,
            UriComponentsBuilder uriBuilder) {
        UserResponseDto user = userService.createUser(userFormDto);

        URI location = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location).body(user);
    }

}
