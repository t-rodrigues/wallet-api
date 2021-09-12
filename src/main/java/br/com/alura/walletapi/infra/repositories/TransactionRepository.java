package br.com.alura.walletapi.infra.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.alura.walletapi.domain.entities.Transaction;

@Repository
public class TransactionRepository {

    private List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> findAll() {
        return transactions;
    }

    public Transaction save(Transaction transaction) {
        transactions.add(transaction);

        return transaction;
    }

}
