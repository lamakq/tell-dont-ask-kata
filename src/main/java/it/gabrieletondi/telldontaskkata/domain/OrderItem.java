package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public class OrderItem {
    private Product product;
    private int quantity;
    private BigDecimal taxedAmount;
    private BigDecimal tax;

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTaxedAmount() {
        return taxedAmount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.taxedAmount = product.getUnitaryTaxedAmount()
                .multiply(BigDecimal.valueOf(quantity))
                .setScale(2, HALF_UP);

        this.tax = product.getUnitaryTax()
                .multiply(BigDecimal.valueOf(quantity));
    }

}
