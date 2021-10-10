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
            + "t.ticker, SUM(CASE WHEN(t.type = 'BUY') THEN t.quantity ELSE(t.quantity * -1) END), "
            + "(SELECT SUM(CASE WHEN(t2.type = 'BUY') THEN t2.quantity ELSE(t2.quantity * -1) END) FROM Transaction t2)"
            + ") FROM Transaction t GROUP BY t.ticker")
    List<WalletItemDto> reportInvestmentPortfolio();

}
