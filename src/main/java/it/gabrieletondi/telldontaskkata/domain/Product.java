package it.gabrieletondi.telldontaskkata.domain;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;

public class Product {

    private String name;
    private BigDecimal price;
    private Category category;

    private BigDecimal unitaryTax;
    private BigDecimal unitaryTaxedAmount;

    public String getName() {
        return name;
    }


    public BigDecimal getPrice() {
        return price;
    }


    public Product(String name, BigDecimal price, Category category) {
        this.category = category;
        this.price = price;
        this.name = name;

        this.unitaryTax = this.price
            .divide(valueOf(100))
            .multiply(this.category.getTaxPercentage())
            .setScale(2, HALF_UP);

        this.unitaryTaxedAmount = this.price
            .add(this.unitaryTax)
            .setScale(2, HALF_UP);
    }

    public BigDecimal getUnitaryTax() {
        return unitaryTax;
    }

    public BigDecimal getUnitaryTaxedAmount() {
        return this.unitaryTaxedAmount;
    }
}
