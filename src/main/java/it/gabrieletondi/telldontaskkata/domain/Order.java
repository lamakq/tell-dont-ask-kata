package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.SellItemRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.CREATED;

public class Order {
    private BigDecimal total;
    private String currency;
    private List<OrderItem> items;
    private BigDecimal tax;
    private OrderStatus status;
    private int id;

    public Order() {
        this.status = CREATED;
        this.items = new ArrayList<>();
        this.currency = "EUR";
        this.total = new BigDecimal("0.00");
        this.tax = new BigDecimal("0.00");
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getCurrency() {
        return currency;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addItem(OrderItem orderItem) {
        items.add(orderItem);

        addTaxedAmount(orderItem.getTaxedAmount());
        addTaxAmount(orderItem.getTax());
    }

    private void addTaxedAmount(BigDecimal taxedAmount) {
        this.total = this.total.add(taxedAmount);
    }

    private void addTaxAmount(BigDecimal taxAmount) {
        this.tax = this.tax.add(taxAmount);
    }

    public void addOrderItem(SellItemRequest itemRequest, Product product) {
        final OrderItem orderItem = itemRequest.getOrderItem(product);
        addItem(orderItem);
    }
}
