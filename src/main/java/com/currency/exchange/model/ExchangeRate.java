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
    private int id;

    @XmlElement(name = "Tp", namespace = "http://www.lb.lt/WebServices/FxRates")
    private String tp;

    @XmlElement(name = "Dt", namespace = "http://www.lb.lt/WebServices/FxRates")
    private Date dt;

    @Transient
    @XmlElement(name = "CcyAmt", namespace = "http://www.lb.lt/WebServices/FxRates")
    private List<CurrencyAmount> ccyAmt = new ArrayList<>();

    @XmlTransient
    private String ccyF;

    @XmlTransient
    private double ccyFAmt;

    @XmlTransient
    private String ccyT;

    @XmlTransient
    private double ccyTAmt;

    public void setValues() {
        this.ccyF = ccyAmt.get(0).getCcy();
        this.ccyFAmt = ccyAmt.get(0).getAmt();
        this.ccyT = ccyAmt.get(1).getCcy();
        this.ccyTAmt = ccyAmt.get(1).getAmt();
    }

}
