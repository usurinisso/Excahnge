package com.currency.exchange.component;

import com.currency.exchange.model.ExchangeRate;
import com.currency.exchange.model.ExchangeRates;
import com.currency.exchange.service.ExchangeRateService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Component
public class ExchangeRateTask extends QuartzJobBean {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private RestTemplate template;

    private static String eUri = "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU";

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ExchangeRates.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            String response = template.getForObject(eUri, String.class);
            ExchangeRates er = (ExchangeRates) jaxbUnmarshaller.unmarshal(new StringReader(response));
            ExchangeRate[] ers = er.getExchangeRate();
            exchangeRateService.saveAllExchangeRates(ers);
        } catch (JAXBException e) {

        }

    }
}
