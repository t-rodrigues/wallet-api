package br.com.alura.walletapi.application.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFormDto {

    @NotBlank
    private String name;

    @Email
    private String email;

}
