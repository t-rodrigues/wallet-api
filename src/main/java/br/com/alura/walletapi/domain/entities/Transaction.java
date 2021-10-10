package br.com.alura.walletapi.domain.entities;

import br.com.alura.walletapi.domain.entities.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ticker;
    private LocalDate date;
    private BigDecimal price;
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    private User user;

    public Transaction() {

    }

    public Transaction(String ticker, LocalDate date, BigDecimal price, Integer quantity, TransactionType type,
            User user) {
        this.ticker = ticker;
        this.date = date;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.user = user;
    }

}
