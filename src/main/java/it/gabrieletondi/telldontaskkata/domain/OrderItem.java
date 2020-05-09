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
        this.tax = getProductTax();
        this.taxedAmount = getProductTaxedAmount();
    }

    private BigDecimal getProductTax() {
        final BigDecimal unitaryTax = product.getUnitaryTax().setScale(2, HALF_UP);
        return unitaryTax.multiply(BigDecimal.valueOf(quantity));
    }

    private BigDecimal getProductTaxedAmount() {
        final BigDecimal unitaryTax = product.getUnitaryTax().setScale(2, HALF_UP);
        final BigDecimal unitaryTaxedAmount = product.getUnitaryTaxedAmount(unitaryTax).setScale(2, HALF_UP);
        return unitaryTaxedAmount.multiply(BigDecimal.valueOf(quantity)).setScale(2, HALF_UP);
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
