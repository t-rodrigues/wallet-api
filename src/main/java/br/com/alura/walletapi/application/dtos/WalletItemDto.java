package br.com.alura.walletapi.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WalletItemDto {

    private String ticker;
    private Long quantity;
    private Double percent;

}
