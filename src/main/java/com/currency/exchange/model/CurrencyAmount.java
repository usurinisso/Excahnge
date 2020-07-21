package com.currency.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Currency_Amount")
@XmlRootElement(name = "CcyAmt", namespace = "http://www.lb.lt/WebServices/FxRates")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyAmount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @XmlElement(name = "Ccy", namespace = "http://www.lb.lt/WebServices/FxRates")
    private String ccy;

    @XmlElement(name = "Amt", namespace = "http://www.lb.lt/WebServices/FxRates")
    private double amt;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @XmlTransient
    private ExchangeRate exchangeRate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    @XmlTransient
    private Currency currency;
}
