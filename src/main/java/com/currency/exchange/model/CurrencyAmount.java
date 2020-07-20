package com.currency.exchange.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Long id;

    @XmlElement(name = "Ccy", namespace = "http://www.lb.lt/WebServices/FxRates")
    private String ccy;

    @XmlElement(name = "Amt", namespace = "http://www.lb.lt/WebServices/FxRates")
    private double amt;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @XmlTransient
    private ExchangeRate exchangeRate;
}
