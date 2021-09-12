package br.com.alura.walletapi.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.alura.walletapi.application.dtos.TransactionFormDto;
import br.com.alura.walletapi.application.dtos.TransactionResponseDto;
import br.com.alura.walletapi.domain.entities.Transaction;
import br.com.alura.walletapi.infra.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private ModelMapper modelMapper = new ModelMapper();

    private final TransactionRepository transactionRepository;

    public List<TransactionResponseDto> getTransactions() {
        var transactions = transactionRepository.findAll();

        return transactions.stream().map(t -> modelMapper.map(t, TransactionResponseDto.class))
                .collect(Collectors.toList());
    }

    public void createTransaction(TransactionFormDto transactionFormDto) {
        var transaction = modelMapper.map(transactionFormDto, Transaction.class);

        transactionRepository.save(transaction);
    }

}
