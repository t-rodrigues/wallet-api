package br.com.alura.walletapi.infra.repositories;

import br.com.alura.walletapi.application.dtos.WalletItemDto;
import br.com.alura.walletapi.domain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT NEW br.com.alura.walletapi.application.dtos.WalletItemDto("
            + "t.ticker, SUM(t.quantity), SUM(t.quantity) * 1.0 / (SELECT SUM(t2.quantity) * 1.0 FROM Transaction t2)) "
            + "FROM Transaction t GROUP BY t.ticker")
    List<WalletItemDto> reportInvestmentPortfolio();

}
