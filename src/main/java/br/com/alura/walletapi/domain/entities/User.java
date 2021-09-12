package br.com.alura.walletapi.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String name;
    private String email;
    private String password;

}
