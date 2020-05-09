package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.SellItemRequest;

import java.math.BigDecimal;

public class OrderItem {
    private Product product;
    private int quantity;
    private BigDecimal taxedAmount;
    private BigDecimal tax;

    public OrderItem(SellItemRequest itemRequest, Product product, BigDecimal taxedAmount, BigDecimal taxAmount) {
        this.product = product;
        this.quantity = itemRequest.getQuantity();
        this.tax = taxAmount;
        this.taxedAmount = taxedAmount;
    }

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
}
