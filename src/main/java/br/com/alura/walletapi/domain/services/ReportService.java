package br.com.alura.walletapi.domain.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.alura.walletapi.application.dtos.WalletItemDto;
import br.com.alura.walletapi.infra.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final TransactionRepository transactionRepository;

    public List<WalletItemDto> reportInvestmentPortfolio() {
        return transactionRepository.reportInvestmentPortfolio();
    }
}
