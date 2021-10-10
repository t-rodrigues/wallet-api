package br.com.alura.walletapi.infra.repositories;

import br.com.alura.walletapi.application.dtos.WalletItemDto;
import br.com.alura.walletapi.domain.entities.Transaction;
import br.com.alura.walletapi.domain.entities.User;
import br.com.alura.walletapi.domain.entities.enums.TransactionType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class TransactionRepositoryTest {

    private final TransactionRepository transactionRepository;
    private final TestEntityManager testEntityManager;

    @Autowired
    public TransactionRepositoryTest(TransactionRepository transactionRepository, TestEntityManager testEntityManager) {
        this.transactionRepository = transactionRepository;
        this.testEntityManager = testEntityManager;
    }

    private User user;
    private Transaction t1;
    private Transaction t2;
    private Transaction t3;
    private Transaction t4;
    private Transaction t5;

    @BeforeEach
    void setUp() {
        user = new User("Rafaela", "rafaela@example.com", "123123");

        testEntityManager.persist(user);

        t1 = new Transaction("ITSA4", LocalDate.now(), new BigDecimal("10.00"), 50, TransactionType.BUY, user);
        t2 = new Transaction("BBSE3", LocalDate.now(), new BigDecimal("22.80"), 80, TransactionType.BUY, user);
        t3 = new Transaction("EGIE3", LocalDate.now(), new BigDecimal("40.00"), 25, TransactionType.BUY, user);
        t4 = new Transaction("ITSA4", LocalDate.now(), new BigDecimal("11.20"), 40, TransactionType.BUY, user);
        t5 = new Transaction("SAPR4", LocalDate.now(), new BigDecimal("4.20"), 120, TransactionType.BUY, user);
    }

    @Test
    void shouldReturnReportInvestmentPortfolio() {
        transactionRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5));

        var relatorio = transactionRepository.reportInvestmentPortfolio();

        Assertions.assertThat(relatorio).hasSize(4)
                .extracting(WalletItemDto::getTicker, WalletItemDto::getQuantity, WalletItemDto::getPercent)
                .containsExactlyInAnyOrder(Assertions.tuple("ITSA4", 90L, new BigDecimal("28.57")),
                        Assertions.tuple("BBSE3", 80L, new BigDecimal("25.40")),
                        Assertions.tuple("EGIE3", 25L, new BigDecimal("7.94")),
                        Assertions.tuple("SAPR4", 120L, new BigDecimal("38.10")));
    }

    @Test
    void shouldReturnReportInvestmentPortfolioConsiderSells() {
        t4.setType(TransactionType.SELL);
        t4.setQuantity(40);

        transactionRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5));

        var relatorio = transactionRepository.reportInvestmentPortfolio();

        Assertions.assertThat(relatorio).hasSize(4)
                .extracting(WalletItemDto::getTicker, WalletItemDto::getQuantity, WalletItemDto::getPercent)
                .containsExactlyInAnyOrder(Assertions.tuple("ITSA4", 10L, new BigDecimal("4.26")),
                        Assertions.tuple("BBSE3", 80L, new BigDecimal("34.04")),
                        Assertions.tuple("EGIE3", 25L, new BigDecimal("10.64")),
                        Assertions.tuple("SAPR4", 120L, new BigDecimal("51.06")));
    }

}
