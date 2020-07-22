package com.currency.exchange.service;

import com.currency.exchange.model.ExchangeRate;

import java.util.List;

public interface ExchangeRateService {
    void saveExchangeRate(ExchangeRate er);
    void saveAllExchangeRates(ExchangeRate[] ers);
    void preloadNewestExchangeRates();
    List<ExchangeRate> getNewestExchangeRates();
    List<ExchangeRate> getAllExchangeRates(String ccy);
    double getExchangeRate(String ccyF, String ccyT, double ex);
}
