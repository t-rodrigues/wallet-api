package br.com.alura.walletapi.domain.services;

import br.com.alura.walletapi.application.dtos.TransactionFormDto;
import br.com.alura.walletapi.application.dtos.TransactionResponseDto;
import br.com.alura.walletapi.domain.entities.Transaction;
import br.com.alura.walletapi.infra.repositories.TransactionRepository;
import br.com.alura.walletapi.infra.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private ModelMapper modelMapper = new ModelMapper();

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public Page<TransactionResponseDto> getTransactions(Pageable pagination) {
        Page<Transaction> transactions = transactionRepository.findAll(pagination);

        return transactions.map(transaction -> modelMapper.map(transaction, TransactionResponseDto.class));
    }

    @Transactional
    public TransactionResponseDto createTransaction(TransactionFormDto transactionFormDto) {
        try {
            Transaction transaction = modelMapper.map(transactionFormDto, Transaction.class);
            transaction.setId(null);
            transaction.setUser(userRepository.getById(transactionFormDto.getUserId()));

            transactionRepository.save(transaction);

            return modelMapper.map(transaction, TransactionResponseDto.class);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("User not found");
        }
    }

}
