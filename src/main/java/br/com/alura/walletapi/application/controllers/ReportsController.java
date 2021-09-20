package br.com.alura.walletapi.application.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.walletapi.application.dtos.WalletItemDto;
import br.com.alura.walletapi.domain.services.ReportService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reports")
public class ReportsController {

    private final ReportService reportService;

    @GetMapping("/portfolio")
    public List<WalletItemDto> reportInvestmentPortfolio() {
        return reportService.reportInvestmentPortfolio();
    }

}
