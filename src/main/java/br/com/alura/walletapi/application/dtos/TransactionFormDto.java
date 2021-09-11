package br.com.alura.walletapi.application.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.walletapi.domain.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionFormDto {

    private String ticker;
    private LocalDate date;
    private BigDecimal price;
    private Integer quantity;
    private TransactionType type;

}
