package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.RoundingMode.HALF_UP;

public class OrderItem {
    private Product product;
    private int quantity;
    private BigDecimal taxedAmount;
    private BigDecimal tax;

    public OrderItem() {

    }

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.tax = calculateTax();
        this.taxedAmount = calculateTaxedAmount();
    }

    private BigDecimal calculateTax() {
        int roundingScale = 2;
        RoundingMode roundingMode = HALF_UP;
        
        final BigDecimal unitaryTax = product.getUnitaryTax().setScale(roundingScale, roundingMode);
        return unitaryTax.multiply(BigDecimal.valueOf(quantity));
    }

    private BigDecimal calculateTaxedAmount() {
        int roundingScale = 2;
        RoundingMode roundingMode = HALF_UP;

        final BigDecimal unitaryTax = product.getUnitaryTax().setScale(roundingScale, roundingMode);
        final BigDecimal unitaryTaxedAmount = product.getUnitaryTaxedAmount(unitaryTax).setScale(roundingScale, roundingMode);
        return unitaryTaxedAmount.multiply(BigDecimal.valueOf(quantity)).setScale(roundingScale, roundingMode);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTaxedAmount() {
        return taxedAmount;
    }

    public void setTaxedAmount(BigDecimal taxedAmount) {
        this.taxedAmount = taxedAmount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
}
