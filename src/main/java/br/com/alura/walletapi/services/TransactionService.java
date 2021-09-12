package br.com.alura.walletapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.alura.walletapi.application.dtos.TransactionFormDto;
import br.com.alura.walletapi.application.dtos.TransactionResponseDto;
import br.com.alura.walletapi.domain.Transaction;

@Service
public class TransactionService {

    private List<Transaction> transactions = new ArrayList<>();
    private ModelMapper modelMapper = new ModelMapper();

    public List<TransactionResponseDto> getTransactions() {
        return transactions.stream().map(t -> modelMapper.map(t, TransactionResponseDto.class))
                .collect(Collectors.toList());
    }

    public void createTransaction(TransactionFormDto transaction) {
        transactions.add(modelMapper.map(transaction, Transaction.class));
    }

}
