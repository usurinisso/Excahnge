package com.currency.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Currency")
@XmlRootElement(name="CcyNtry", namespace = "http://www.lb.lt/WebServices/FxRates")
@XmlAccessorType(XmlAccessType.FIELD)
public class Currency
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlElement(name="CcyMnrUnts", namespace = "http://www.lb.lt/WebServices/FxRates")
    private String ccyMnrUnts;
    @XmlElement(name="CcyNm", namespace = "http://www.lb.lt/WebServices/FxRates")
    private String ccyNm;
    @XmlElement(name="Ccy", namespace = "http://www.lb.lt/WebServices/FxRates")
    private String ccy;
    @XmlElement(name="CcyNbr", namespace = "http://www.lb.lt/WebServices/FxRates")
    private String ccyNbr;
}