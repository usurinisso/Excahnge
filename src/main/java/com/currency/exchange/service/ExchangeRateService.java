package com.currency.exchange.service;

import com.currency.exchange.model.ExchangeRate;

public interface ExchangeRateService {
    void saveExchangeRate(ExchangeRate er);
    void saveAllExchangeRates(ExchangeRate[] ers);
}
