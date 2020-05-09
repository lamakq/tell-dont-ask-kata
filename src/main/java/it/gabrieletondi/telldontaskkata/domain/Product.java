package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

public class Product {
    private String name;
    private BigDecimal price;
    private Category category;

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


    public void setCategory(Category category) {
        this.category = category;
    }

    private BigDecimal getUnitaryTax() {
        return this.price.divide(valueOf(100)).multiply(this.category.getTaxPercentage()).setScale(2, HALF_UP);
    }

    private BigDecimal getUnitaryTaxedAmount() {
        return this.price.add(getUnitaryTax()).setScale(2, HALF_UP);
    }

    public BigDecimal getTaxAmount(int quantity) {
        return getUnitaryTax().multiply(BigDecimal.valueOf(quantity));
    }

    public BigDecimal getTaxedAmount(int quantity) {
        return getUnitaryTaxedAmount().multiply(BigDecimal.valueOf(quantity)).setScale(2, HALF_UP);
    }
}
