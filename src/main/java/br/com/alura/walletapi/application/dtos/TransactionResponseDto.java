package br.com.alura.walletapi.application.dtos;

import java.math.BigDecimal;

import br.com.alura.walletapi.domain.entities.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponseDto {

    private Long id;
    private String ticker;
    private BigDecimal price;
    private Integer quantity;
    private TransactionType type;
    private UserResponseDto user;

}
