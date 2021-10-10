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

    @BeforeEach
    void setUp() throws Exception {
        User user = new User("Rafaela", "rafaela@example.com", "123123");

        testEntityManager.persist(user);

        Transaction t1 = new Transaction("ITSA4", LocalDate.now(), new BigDecimal("10.00"), 50, TransactionType.BUY,
                user);
        Transaction t2 = new Transaction("BBSE3", LocalDate.now(), new BigDecimal("22.80"), 80, TransactionType.BUY,
                user);
        Transaction t3 = new Transaction("EGIE3", LocalDate.now(), new BigDecimal("40.00"), 25, TransactionType.BUY,
                user);
        Transaction t4 = new Transaction("ITSA4", LocalDate.now(), new BigDecimal("11.20"), 40, TransactionType.BUY,
                user);
        Transaction t5 = new Transaction("SAPR4", LocalDate.now(), new BigDecimal("4.20"), 120, TransactionType.BUY,
                user);

        testEntityManager.persist(t1);
        testEntityManager.persist(t2);
        testEntityManager.persist(t3);
        testEntityManager.persist(t4);
        testEntityManager.persist(t5);
    }

    @Test
    void shouldReturnReportInvestmentPortfolio() {
        var relatorio = transactionRepository.reportInvestmentPortfolio();

        Assertions.assertThat(relatorio).hasSize(4)
                .extracting(WalletItemDto::getTicker, WalletItemDto::getQuantity, WalletItemDto::getPercent)
                .containsExactlyInAnyOrder(Assertions.tuple("ITSA4", 90L, new BigDecimal("28.57")),
                        Assertions.tuple("BBSE3", 80L, new BigDecimal("25.40")),
                        Assertions.tuple("EGIE3", 25L, new BigDecimal("7.94")),
                        Assertions.tuple("SAPR4", 120L, new BigDecimal("38.10")));
    }

}
