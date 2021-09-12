package br.com.alura.walletapi.application.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import br.com.alura.walletapi.application.dtos.TransactionFormDto;
import br.com.alura.walletapi.application.dtos.TransactionResponseDto;
import br.com.alura.walletapi.services.TransactionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public List<TransactionResponseDto> list() {
        return transactionService.getTransactions();
    }

    @PostMapping
    public void create(@RequestBody @Valid TransactionFormDto transaction) {
        transactionService.createTransaction(transaction);
    }

}
