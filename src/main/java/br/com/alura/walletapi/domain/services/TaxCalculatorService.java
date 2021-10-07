package br.com.alura.walletapi.domain.services;

import br.com.alura.walletapi.domain.entities.Transaction;
import br.com.alura.walletapi.domain.entities.enums.TransactionType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TaxCalculatorService {

    public BigDecimal calc(Transaction transaction) {
        if (transaction.getType() == TransactionType.BUY) {
            return BigDecimal.ZERO;
        }

        BigDecimal transactionAmount = transaction.getPrice().multiply(BigDecimal.valueOf(transaction.getQuantity()));

        if (transactionAmount.compareTo(BigDecimal.valueOf(20000.0)) < 0) {
            return BigDecimal.ZERO;
        }

        return transactionAmount.multiply(BigDecimal.valueOf(0.15)).setScale(2, RoundingMode.HALF_UP);
    }

}
