package com.currency.exchange.service;

import com.currency.exchange.model.CurrencyAmount;
import com.currency.exchange.model.ExchangeRate;
import com.currency.exchange.repository.CurrencyAmountRepository;
import com.currency.exchange.repository.CurrencyRepository;
import com.currency.exchange.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final CurrencyAmountRepository currencyAmountRepository;


    @Autowired
    public ExchangeRateServiceImpl (ExchangeRateRepository exchangeRateRepository, CurrencyAmountRepository currencyAmountRepository) {
        this.currencyAmountRepository = currencyAmountRepository;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public void saveExchangeRate(ExchangeRate er) {
        exchangeRateRepository.save(er);
    }

    @Override
    public void saveAllExchangeRates(ExchangeRate[] ers) {
        Arrays.asList(ers).forEach(item->item.setParent());
        exchangeRateRepository.saveAll(Arrays.asList(ers));
    }
}
