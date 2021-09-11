package br.com.alura.walletapi.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.walletapi.domain.enums.TransactionType;

public class Transaction {

    private String ticker;
    private LocalDate date;
    private BigDecimal price;
    private Integer quantity;
    private TransactionType type;

    public Transaction() {
    }

    public Transaction(String ticker, LocalDate date, BigDecimal price, Integer quantity, TransactionType type) {
        this.ticker = ticker;
        this.date = date;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    @Override
    public String toString() {
        return "Transaction [date=" + date + ", price=" + price + ", quantity=" + quantity + ", ticker=" + ticker
                + ", type=" + type + "]";
    }

}
