package com.currency.exchange.controller;

import com.currency.exchange.model.ExchangeRate;
import com.currency.exchange.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class MainController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping("/")
    public ModelAndView main() {
        List<ExchangeRate> exchangeRates = exchangeRateService.getNewestExchangeRates();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exchangeRate", exchangeRates);
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @GetMapping("/{ccy}")
    public ModelAndView exchangeRates(@PathVariable (name = "ccy") String ccy) {
        List<ExchangeRate> exchangeRates = exchangeRateService.getAllExchangeRates(ccy);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exchangeRate", exchangeRates);
        modelAndView.setViewName("currency");
        return modelAndView;
    }
    @PostMapping("/")
    public ModelAndView exchangeRate(
            @RequestParam(name = "examount", required = false) String exAmt,
            @RequestParam(name="erccyf", required = false) String ccyF,
            @RequestParam(name="erccyt", required = false) String ccyT) {
        String pattern = "^(?:[1-9]\\d*|0)?(?:\\.\\d+)?$";
        List<ExchangeRate> exchangeRates = exchangeRateService.getNewestExchangeRates();
        ModelAndView modelAndView = new ModelAndView();
        if(ccyF == null) modelAndView.addObject("msg", "Please provide currency from which you want to convert");
        else if(ccyT == null) modelAndView.addObject("msg", "Please provide currency to which you want to convert");
        else if(exAmt == null) modelAndView.addObject("msg", "Please provide the amount which you want to convert");
        else if(!exAmt.matches(pattern)) modelAndView.addObject("msg", "Please provide a valid amount which you want to convert");
        else {
            double exA = Double.parseDouble(exAmt);
            double exRate = exchangeRateService.getExchangeRate(ccyF, ccyT, exA);
            modelAndView.addObject("msg", exA +" "+ ccyF + " = " + exRate + " " + ccyT);
        }
        modelAndView.addObject("exchangeRate", exchangeRates);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
