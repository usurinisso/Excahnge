package com.currency.exchange.repository;

import com.currency.exchange.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer> {
}
