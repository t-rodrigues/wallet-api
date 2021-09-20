package br.com.alura.walletapi.domain.services;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<UserResponseDto> getUsers(Pageable pagination) {
        Page<User> users = userRepository.findAll(pagination);

        return users.map(user -> modelMapper.map(user, UserResponseDto.class));
    }

    public void createUser(UserFormDto userFormDto) {
        User newUser = modelMapper.map(userFormDto, User.class);
        String password = new Random().nextInt(99999) + "";

        newUser.setPassword(password);
        userRepository.save(newUser);
    }

}
