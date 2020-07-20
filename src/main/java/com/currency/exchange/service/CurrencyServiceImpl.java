package com.currency.exchange.service;

import com.currency.exchange.model.Currency;
import com.currency.exchange.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CurrencyServiceImpl implements CurrencyService{

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl (CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void saveCurrency(Currency ccy) {
        currencyRepository.save(ccy);
    }

    @Override
    public void saveAllCurrencies(Currency[] ccs) {
        currencyRepository.saveAll(Arrays.asList(ccs));
    }
}
