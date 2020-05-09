package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.orderApproval.*;
import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {
    private BigDecimal total;
    private String currency;
    private List<OrderItem> items;
    private BigDecimal tax;
    private OrderStatus status;
    private int id;

    private List<ApprovalValidator> approvalValidators;

    public Order(String currency) {
        this.status = OrderStatus.CREATED;
        this.items = new ArrayList<>();
        this.total = BigDecimal.ZERO;
        this.tax = BigDecimal.ZERO;
        this.currency = currency;

        approvalValidators = Arrays.asList(
                new ApprovalValidatorAlreadyShipped(),
                new ApprovalValidatorRejectedOrder(),
                new ApprovalValidatorApprovedOrder()
        );
    }

    public Order() {
        this("EUR");
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
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

    public void addItem(OrderItem orderItem)
    {
        items.add(orderItem);
        total = total.add(orderItem.getTaxedAmount());
        tax = tax.add(orderItem.getTax());
    }

    public void approve(OrderApprovalRequest request) {

        approvalValidators.forEach(validator -> validator.validate(request, this));
        status = request.isApproved() ? OrderStatus.APPROVED : OrderStatus.REJECTED;
    }
}
