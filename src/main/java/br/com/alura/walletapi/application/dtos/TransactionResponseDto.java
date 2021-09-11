package br.com.alura.walletapi.application.dtos;

import java.math.BigDecimal;

import br.com.alura.walletapi.domain.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponseDto {

    private String ticker;
    private BigDecimal price;
    private Integer quantity;
    private TransactionType type;

}