package br.com.alura.walletapi.application.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAlias;

import br.com.alura.walletapi.domain.entities.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionFormDto {

    @NotNull
    @Size(min = 5, max = 6)
    private String ticker;

    @PastOrPresent
    private LocalDate date;

    @Positive
    private BigDecimal price;

    @Positive
    private Integer quantity;

    @NotNull
    private TransactionType type;

    @JsonAlias("user_id")
    private Long userId;

}
