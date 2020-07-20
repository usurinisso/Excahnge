package com.currency.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="CcyTbl", namespace = "http://www.lb.lt/WebServices/FxRates")
@XmlAccessorType(XmlAccessType.FIELD)
public class Currencies {
    @XmlElement(name = "CcyNtry", namespace = "http://www.lb.lt/WebServices/FxRates")
    private Currency[] currency;
}
