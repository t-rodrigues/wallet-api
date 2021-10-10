package br.com.alura.walletapi.application.dtos;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class WalletItemDto {

    private String ticker;
    private Long quantity;
    private BigDecimal percent;

    public WalletItemDto(String ticker, Long quantity, Double percent) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.percent = BigDecimal.valueOf(percent).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
    }

}
