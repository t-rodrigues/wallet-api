package br.com.alura.walletapi.services;

import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.alura.walletapi.application.dtos.UserFormDto;
import br.com.alura.walletapi.application.dtos.UserResponseDto;
import br.com.alura.walletapi.domain.User;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();
    private ModelMapper modelMapper = new ModelMapper();

    public List<UserResponseDto> getUsers() {
        return users.stream().map(user -> modelMapper.map(user, UserResponseDto.class)).collect(Collectors.toList());
    }

    public void createUser(UserFormDto user) {
        User newUser = modelMapper.map(user, User.class);
        String password = new Random().nextInt(99999) + "";

        newUser.setPassword(password);

        users.add(newUser);
    }

}
