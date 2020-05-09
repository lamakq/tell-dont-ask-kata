package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;
import it.gabrieletondi.telldontaskkata.service.ShipmentService;
import it.gabrieletondi.telldontaskkata.useCase.*;

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

    public Order(){}

    public Order(String currency){
        this.status = OrderStatus.CREATED;
        this.items = new ArrayList<>();
        this.currency = currency;
        this.total = new BigDecimal("0.00");
        this.tax = new BigDecimal("0.00");
    }

    public void approveOrder(boolean isApproved){
        if (this.status.equals(OrderStatus.SHIPPED)) {
            throw new ShippedOrdersCannotBeChangedException();
        }

        if (isApproved && this.status.equals(OrderStatus.REJECTED)) {
            throw new RejectedOrderCannotBeApprovedException();
        }

        if (!isApproved && this.status.equals(OrderStatus.APPROVED)) {
            throw new ApprovedOrderCannotBeRejectedException();
        }

        this.status = isApproved ? OrderStatus.APPROVED : OrderStatus.REJECTED;
    }

    public void ship(ShipmentService shipmentService){
        if (this.status.equals(CREATED) || this.status.equals(REJECTED)) {
            throw new OrderCannotBeShippedException();
        }

        if (this.status.equals(SHIPPED)) {
            throw new OrderCannotBeShippedTwiceException();
        }

        shipmentService.ship(this);

        this.status = OrderStatus.SHIPPED;
    }

    private void addOrderItem(OrderItem orderItem) {
        this.items.add(orderItem);
        this.total = this.total.add(orderItem.getTaxedAmount());
        this.tax = this.tax.add(orderItem.getTax());

    }

    public void createOrder(ProductCatalog productCatalog, SellItemsRequest request){
        for (SellItemRequest itemRequest : request.getRequests()) {
            Product product = productCatalog.getByName(itemRequest.getProductName());
            final OrderItem orderItem = new OrderItem(product, itemRequest.getQuantity());
            this.addOrderItem(orderItem);
        }
    }
}
