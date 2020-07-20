package com.currency.exchange.repository;

import com.currency.exchange.model.CurrencyAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyAmountRepository extends JpaRepository<CurrencyAmount, Integer> {
    @Query(value = "FROM CurrencyAmount b where b.exchangeRate.id = ?1")
    public List<CurrencyAmount> getCurrencyAmountByExchangeRateId(Long id);
}
