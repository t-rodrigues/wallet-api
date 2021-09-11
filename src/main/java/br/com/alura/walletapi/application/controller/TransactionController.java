package br.com.alura.walletapi.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import br.com.alura.walletapi.application.dtos.TransactionFormDto;
import br.com.alura.walletapi.application.dtos.TransactionResponseDto;
import br.com.alura.walletapi.domain.Transaction;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private List<Transaction> transactions = new ArrayList<>();
    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public List<TransactionResponseDto> list() {
        return transactions.stream().map(t -> modelMapper.map(t, TransactionResponseDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody TransactionFormDto transaction) {
        transactions.add(modelMapper.map(transaction, Transaction.class));
    }

}
