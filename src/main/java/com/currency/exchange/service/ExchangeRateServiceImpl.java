package com.currency.exchange.service;

import com.currency.exchange.model.Currency;
import com.currency.exchange.model.ExchangeRate;
import com.currency.exchange.repository.CurrencyRepository;
import com.currency.exchange.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeMath.round;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private List<ExchangeRate> ers = new ArrayList<>();

    private final ExchangeRateRepository exchangeRateRepository;
    private final CurrencyRepository currencyRepository;

    @Autowired
    public ExchangeRateServiceImpl (ExchangeRateRepository exchangeRateRepository, CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public void saveExchangeRate(ExchangeRate er) {
        exchangeRateRepository.save(er);
    }

    @Override
    public void saveAllExchangeRates(ExchangeRate[] ers) {
        Arrays.asList(ers).forEach(item-> item.setValues());
        exchangeRateRepository.saveAll(Arrays.asList(ers));
    }

    @Override
    public void preloadNewestExchangeRates() {
        ers.clear();
        currencyRepository.findAll().forEach(item->{
            List<ExchangeRate> er = exchangeRateRepository.getExchangeRateByCcy(item.getCcy());
            if(er.size() != 0) ers.add(er.get(0));
        });
    }

    @Override
    public List<ExchangeRate> getNewestExchangeRates() {
        return ers;
    }

    @Override
    public List<ExchangeRate> getAllExchangeRates(String ccy) {
        return exchangeRateRepository.getExchangeRateByCcy(ccy);
    }
    @Override
    public double getExchangeRate(String ccyF, String ccyT, double ex) {
        List<ExchangeRate> er = new ArrayList<>();
        exchangeRateRepository.findAll().forEach(item->{
            if(item.getCcyT().equals(ccyF) || item.getCcyT().equals(ccyT)) er.add(item);
        });
        for (ExchangeRate e: er) {
            if(e.getCcyT().equals(ccyF)) {
                ex = ex / e.getCcyTAmt();
                break;
            }
        }
        for (ExchangeRate e: er) {
            if(e.getCcyT().equals(ccyT)) {
                ex = ex * e.getCcyTAmt();
                break;
            }
        }
        return ex;
    }
}
