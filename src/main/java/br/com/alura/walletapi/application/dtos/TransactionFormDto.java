package br.com.alura.walletapi.application.dtos;

import br.com.alura.walletapi.domain.entities.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionFormDto {

    @NotNull
    @Size(min = 5, max = 6)
    @Pattern(regexp = "[a-zA-Z]{4}[0-9][0-9]?")
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
