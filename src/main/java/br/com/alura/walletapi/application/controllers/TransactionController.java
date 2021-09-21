package br.com.alura.walletapi.application.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<TransactionResponseDto> create(@RequestBody @Valid TransactionFormDto transactionFormDto,
            UriComponentsBuilder uriBuilder) {
        TransactionResponseDto transactionResponseDto = transactionService.createTransaction(transactionFormDto);

        URI location = uriBuilder.path("/transactions/{id}").buildAndExpand(transactionResponseDto.getId()).toUri();

        return ResponseEntity.created(location).body(transactionResponseDto);

    }

}
