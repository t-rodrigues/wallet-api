package br.com.alura.walletapi.domain.services;

import br.com.alura.walletapi.domain.entities.Transaction;
import br.com.alura.walletapi.domain.entities.User;
import br.com.alura.walletapi.domain.entities.enums.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxCalculatorServiceTest {

    private TaxCalculatorService taxCalculatorService;

    private Transaction createTransaction(BigDecimal price, Integer quantity, TransactionType type) {
        return new Transaction("ITSA4", LocalDate.now(), price, quantity, type, new User(null, null, null));
    }

    @BeforeEach
    void setUp() {
        taxCalculatorService = new TaxCalculatorService();
    }

    @Test
    void shouldNotHaveTaxWhenTransactionTypeIsBuy() {
        Transaction transaction = createTransaction(new BigDecimal("30.0"), 10, TransactionType.BUY);

        BigDecimal tax = taxCalculatorService.calc(transaction);

        assertEquals(BigDecimal.ZERO, tax);
    }

    @Test
    void shouldNotHaveTaxWhenTransactionAmountIsLessThan20000() {
        Transaction transaction = createTransaction(new BigDecimal("30.0"), 10, TransactionType.SELL);

        BigDecimal tax = taxCalculatorService.calc(transaction);

        assertEquals(BigDecimal.ZERO, tax);
    }

    @Test
    void shouldReturnCorrectTaxWhenTransactionAmountIsMoreOrEqualThan20000() {
        Transaction transaction = createTransaction(new BigDecimal("20.0"), 1000, TransactionType.SELL);

        BigDecimal tax = taxCalculatorService.calc(transaction);

        assertEquals(BigDecimal.valueOf(3000.00).setScale(2), tax);
    }

}
