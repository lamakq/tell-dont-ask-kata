package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

public class Product {
    private String name;
    private BigDecimal price;
    private TaxPercentage taxPercentage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public void setTaxPercentage(TaxPercentage taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    private BigDecimal getUnitaryTaxedAmount() {
        return this.price.add(taxPercentage.getUnitaryTaxOn(price)).setScale(2, HALF_UP);
    }

    public BigDecimal getTaxAmount(int quantity) {
        return taxPercentage.getUnitaryTaxOn(price).multiply(BigDecimal.valueOf(quantity));
    }

    public BigDecimal getTaxedAmount(int quantity) {
        return getUnitaryTaxedAmount().multiply(BigDecimal.valueOf(quantity)).setScale(2, HALF_UP);
    }
}
