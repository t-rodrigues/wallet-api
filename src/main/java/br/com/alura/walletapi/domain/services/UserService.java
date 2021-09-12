package br.com.alura.walletapi.domain.services;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.alura.walletapi.application.dtos.UserFormDto;
import br.com.alura.walletapi.application.dtos.UserResponseDto;
import br.com.alura.walletapi.domain.entities.User;
import br.com.alura.walletapi.infra.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private ModelMapper modelMapper = new ModelMapper();

    private final UserRepository userRepository;

    public List<UserResponseDto> getUsers() {
        var users = userRepository.findAll();

        return users.stream().map(user -> modelMapper.map(user, UserResponseDto.class)).collect(Collectors.toList());
    }

    public void createUser(UserFormDto userFormDto) {
        User newUser = modelMapper.map(userFormDto, User.class);
        String password = new Random().nextInt(99999) + "";

        newUser.setPassword(password);
        userRepository.save(newUser);
    }

}
