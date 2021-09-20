package br.com.alura.walletapi.domain.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.walletapi.application.dtos.TransactionFormDto;
import br.com.alura.walletapi.application.dtos.TransactionResponseDto;
import br.com.alura.walletapi.domain.entities.Transaction;
import br.com.alura.walletapi.infra.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public Page<TransactionResponseDto> getTransactions(Pageable pagination) {
        Page<Transaction> transactions = transactionRepository.findAll(pagination);

        return transactions.map(transaction -> modelMapper.map(transaction, TransactionResponseDto.class));
    }

    @Transactional
    public void createTransaction(TransactionFormDto transactionFormDto) {
        Transaction transaction = modelMapper.map(transactionFormDto, Transaction.class);
        transaction.setId(null);

        transactionRepository.save(transaction);
    }

}
