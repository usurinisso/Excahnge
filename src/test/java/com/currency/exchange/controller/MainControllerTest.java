package com.currency.exchange.controller;

import com.currency.exchange.component.DataLoader;
import com.currency.exchange.model.ExchangeRate;
import com.currency.exchange.service.ExchangeRateService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class MainControllerTest {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void main() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void exchangeRatesNotFound() throws Exception{
        mockMvc.perform(get("/{ccy}", "uuu"))
                .andExpect(status().isOk())
                .andExpect(view().name("currency"))
                .andExpect(model().attribute("msg", "Currency uuu NOT FOUND"));

    }
    @Test
    public void exchangeRatesFound() throws Exception{
        mockMvc.perform(get("/{ccy}", "USD"))
                .andExpect(status().isOk())
                .andExpect(view().name("currency"))
                .andExpect(model().attribute("exchangeRate", exchangeRateService.getAllExchangeRates("USD")));
    }
    @Test
    public void exchangeRateNoCcyF () throws Exception {
        mockMvc.perform(post("/").param("ccyF", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("msg", "Please provide currency from which you want to convert"));
    }
    @Test
    public void exchangeRateNoCcyT () throws Exception {
        mockMvc.perform(post("/").param("ccyF", "USD")
                .param("exAmt", "1")
                .param("ccyT", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("msg", "Please provide currency to which you want to convert"));
    }
    @Test
    public void exchangeRateExAmt () throws Exception {
        mockMvc.perform(post("/").param("ccyF", "USD").param("exAmt", "").param("ccyT", "USD"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("msg", "Please provide the amount which you want to convert"));
    }

}