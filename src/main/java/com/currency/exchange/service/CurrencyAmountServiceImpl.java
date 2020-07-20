package com.currency.exchange.service;

import com.currency.exchange.model.CurrencyAmount;
import com.currency.exchange.repository.CurrencyAmountRepository;
import com.currency.exchange.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyAmountServiceImpl implements CurrencyAmountService {

    private final CurrencyAmountRepository currencyAmountRepository;
    @Autowired
    public CurrencyAmountServiceImpl (CurrencyAmountRepository currencyAmountRepository) {
        this.currencyAmountRepository = currencyAmountRepository;
    }

    @Override
    public void save(CurrencyAmount cAmt) {
        currencyAmountRepository.save(cAmt);
    }

}
