package br.com.alura.walletapi.domain.services;

import br.com.alura.walletapi.application.dtos.TransactionFormDto;
import br.com.alura.walletapi.application.dtos.TransactionResponseDto;
import br.com.alura.walletapi.domain.entities.enums.TransactionType;
import br.com.alura.walletapi.infra.repositories.TransactionRepository;
import br.com.alura.walletapi.infra.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TransactionService transactionService;

    private long validUserId = 1L;
    private long invalidUserId = 666L;

    @Test
    void shouldRegisterNewTransaction() {
        TransactionFormDto transactionFormDto = new TransactionFormDto("ITSA4", LocalDate.now(),
                BigDecimal.valueOf(10.45), 5, TransactionType.BUY, validUserId);

        TransactionResponseDto transactionResponseDto = transactionService.createTransaction(transactionFormDto);

        assertEquals(transactionFormDto.getTicker(), transactionResponseDto.getTicker());
        assertEquals(transactionFormDto.getQuantity(), transactionResponseDto.getQuantity());
        assertEquals(transactionFormDto.getPrice(), transactionResponseDto.getPrice());
        assertEquals(transactionFormDto.getType(), transactionResponseDto.getType());
        Mockito.verify(transactionRepository, times(1)).save(Mockito.any());
    }

    @Test
    void shouldNotRegisterNewTransactionWhenUserIdIsInvalid() {
        TransactionFormDto transactionFormDto = new TransactionFormDto("ITSA4", LocalDate.now(),
                BigDecimal.valueOf(10.45), 5, TransactionType.BUY, invalidUserId);

        Mockito.when(userRepository.getById(invalidUserId)).thenThrow(EntityNotFoundException.class);

        assertThrows(IllegalArgumentException.class, () -> transactionService.createTransaction(transactionFormDto));
    }

}
