package br.com.alura.walletapi.domain.services;

import br.com.alura.walletapi.domain.entities.Transaction;
import br.com.alura.walletapi.domain.entities.enums.TransactionType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxCalculatorServiceTest {

    @Test
    void shouldNotHaveTaxWhenTransactionTypeIsBuy() {
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.BUY);

        TaxCalculatorService taxCalculatorService = new TaxCalculatorService();
        BigDecimal tax = taxCalculatorService.calc(transaction);

        assertEquals(BigDecimal.ZERO, tax);
    }

    @Test
    void shouldNotHaveTaxWhenTransactionAmountIsLessThan20000() {
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.SELL);
        transaction.setPrice(new BigDecimal("30.0"));
        transaction.setQuantity(10);

        TaxCalculatorService taxCalculatorService = new TaxCalculatorService();
        BigDecimal tax = taxCalculatorService.calc(transaction);

        assertEquals(BigDecimal.ZERO, tax);
    }

    @Test
    void shouldReturnCorrectTaxWhenTransactionAmountIsMoreOrEqualThan20000() {
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.SELL);
        transaction.setPrice(new BigDecimal("20.0"));
        transaction.setQuantity(1000);

        TaxCalculatorService taxCalculatorService = new TaxCalculatorService();
        BigDecimal tax = taxCalculatorService.calc(transaction);

        assertEquals(BigDecimal.valueOf(3000.00).setScale(2), tax);
    }

}
