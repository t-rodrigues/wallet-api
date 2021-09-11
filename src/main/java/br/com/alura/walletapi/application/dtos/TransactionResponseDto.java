package br.com.alura.walletapi.application.dtos;

import java.math.BigDecimal;

import br.com.alura.walletapi.domain.enums.TransactionType;

public class TransactionResponseDto {

    private String ticker;
    private BigDecimal price;
    private Integer quantity;
    private TransactionType type;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

}
