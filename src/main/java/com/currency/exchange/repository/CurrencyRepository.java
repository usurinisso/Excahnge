package com.currency.exchange.repository;

import com.currency.exchange.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    @Query(value = "FROM Currency b where b.ccy = ?1")
    public Currency getCurrencyByCcy(String ccy);
}
