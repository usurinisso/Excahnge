package com.currency.exchange.repository;

import com.currency.exchange.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer> {
    @Query("FROM ExchangeRate b where b.ccyT = ?1 ORDER BY b.dt DESC")
    public List<ExchangeRate> getExchangeRateByCcy(String ccy);
}
