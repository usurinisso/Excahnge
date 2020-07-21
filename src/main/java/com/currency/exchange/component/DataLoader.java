package com.currency.exchange.component;

import com.currency.exchange.model.Currency;
import com.currency.exchange.model.Currencies;
import com.currency.exchange.model.ExchangeRate;
import com.currency.exchange.model.ExchangeRates;
import com.currency.exchange.service.CurrencyService;
import com.currency.exchange.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private RestTemplate template;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ExchangeRateService exchangeRateService;


    private static String cUri = "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrencyList";
    private static String dateNow = java.time.LocalDate.now().toString();
    private static String eUri = "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getFxRatesForCurrency?tp=EU&ccy=&dtFrom=2020-07-10&dtTo=" + dateNow;

    public void run(ApplicationArguments args) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Currencies.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        String response = template.getForObject(cUri, String.class);
        Currencies cs = (Currencies) jaxbUnmarshaller.unmarshal(new StringReader(response));
        Currency[] ccs = cs.getCurrency();
        currencyService.saveAllCurrencies(ccs);
        JAXBContext jaxbContext1 = JAXBContext.newInstance(ExchangeRates.class);
        Unmarshaller jaxbUnmarshaller1 = jaxbContext1.createUnmarshaller();
        String response1 = template.getForObject(eUri, String.class);
        ExchangeRates er = (ExchangeRates) jaxbUnmarshaller1.unmarshal(new StringReader(response1));
        ExchangeRate[] ers = er.getExchangeRate();
        exchangeRateService.saveAllExchangeRates(ers);

    }
}
