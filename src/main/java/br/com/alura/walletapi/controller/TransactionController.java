package br.com.alura.walletapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import br.com.alura.walletapi.domain.Transaction;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private List<Transaction> transactions = new ArrayList<>();

    @GetMapping
    public List<Transaction> list() {
        return transactions;
    }

    @PostMapping
    public void create(@RequestBody Transaction transaction) {
        transactions.add(transaction);
    }

}
