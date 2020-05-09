package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

public class TaxPercentage {
    private BigDecimal value;

    public TaxPercentage(BigDecimal value) {
        this.value = value;
    }

    BigDecimal getUnitaryTaxOn(BigDecimal price) {
        BigDecimal dividedPrice = price.divide(valueOf(100));
        BigDecimal unitaryTax = dividedPrice.multiply(value);
        return unitaryTax.setScale(2, HALF_UP);
    }
}
