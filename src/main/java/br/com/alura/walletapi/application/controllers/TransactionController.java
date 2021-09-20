package br.com.alura.walletapi.application.controllers;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import br.com.alura.walletapi.application.dtos.TransactionFormDto;
import br.com.alura.walletapi.application.dtos.TransactionResponseDto;
import br.com.alura.walletapi.domain.services.TransactionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public Page<TransactionResponseDto> list(
            @PageableDefault(size = 10, sort = "ticker", direction = Direction.ASC) Pageable pagination) {
        return transactionService.getTransactions(pagination);
    }

    @PostMapping
    public void create(@RequestBody @Valid TransactionFormDto transaction) {
        transactionService.createTransaction(transaction);
    }

}
