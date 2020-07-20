package com.currency.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Exchange_Rate")
@XmlRootElement(name="FxRate", namespace = "http://www.lb.lt/WebServices/FxRates")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @XmlElement(name = "Tp", namespace = "http://www.lb.lt/WebServices/FxRates")
    private String tp;

    @XmlElement(name = "Dt", namespace = "http://www.lb.lt/WebServices/FxRates")
    private Date dt;

    @XmlElement(name = "CcyAmt", namespace = "http://www.lb.lt/WebServices/FxRates")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exchangeRate")
    private List<CurrencyAmount> ccyAmt = new ArrayList<>();

    public void setParent() {
        ccyAmt.forEach(item->item.setExchangeRate(this));
    }

}
