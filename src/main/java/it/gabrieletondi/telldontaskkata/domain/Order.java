package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.exception.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.*;

public class Order {
    private BigDecimal total;
    private String currency;
    private List<OrderItem> items;
    private BigDecimal tax;
    private OrderStatus status;
    private int id;

    public Order() {
        this.currency = "EUR";
        this.status = OrderStatus.CREATED;
        this.items = new ArrayList<>();
        this.total = new BigDecimal("0.00");
        this.tax = new BigDecimal("0.00");
    }

    public Order(String currency) {
        this.currency = currency;
        this.status = OrderStatus.CREATED;
        this.items = new ArrayList<>();
        this.total = new BigDecimal("0.00");
        this.tax = new BigDecimal("0.00");
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
        if (this.status.equals(OrderStatus.SHIPPED)) {
            throw new ShippedOrdersCannotBeChangedException();
        }

        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addItem(OrderItem orderItem) {
        this.items.add(orderItem);
    }

    public void addTotal(BigDecimal taxedAmount) {
        this.total = this.total.add(taxedAmount);
    }

    public void addTaxTotal(BigDecimal tax) {
        this.tax = this.tax.add(tax);
    }

    public void approve() {
        if (status.equals(OrderStatus.REJECTED)) {
            throw new RejectedOrderCannotBeApprovedException();
        }
        setStatus(OrderStatus.APPROVED);
    }

    public void reject() {
        if (status.equals(OrderStatus.APPROVED)) {
            throw new ApprovedOrderCannotBeRejectedException();
        }
        setStatus(OrderStatus.REJECTED);
    }

    public  void checkShipping(){

        if (status.equals(CREATED) || status.equals(REJECTED)) {
            throw new OrderCannotBeShippedException();
        }
        if (status.equals(SHIPPED)) {
            throw new OrderCannotBeShippedTwiceException();
        }

    }
}
