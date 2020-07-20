package com.currency.exchange.service;

import com.currency.exchange.model.Currency;

public interface CurrencyService {
    void saveCurrency(Currency ccy);
    void saveAllCurrencies(Currency[] ccs);
}
