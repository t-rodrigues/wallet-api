package br.com.alura.walletapi.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;

import br.com.alura.walletapi.domain.entities.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

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

}
