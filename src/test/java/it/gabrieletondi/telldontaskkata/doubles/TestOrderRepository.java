package it.gabrieletondi.telldontaskkata.doubles;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;

import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;
import java.util.ArrayList;
import java.util.List;

public class TestOrderRepository implements OrderRepository {
    private Order insertedOrder;
    private List<Order> orders = new ArrayList<>();

    public Order getSavedOrder() {
        return insertedOrder;
    }

    public void save(Order order) {
        this.insertedOrder = order;
    }

    @Override
    public Order getById(int orderId) {
        return orders.stream().filter(o -> o.getId() == orderId).findFirst().get();
    }

    @Override
    public void approveOrder(OrderApprovalRequest orderApprovalRequest) {
        final Order order = this.getById(orderApprovalRequest.getOrderId());
        order.approveOrder(orderApprovalRequest.isApproved());
        this.save(order);
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }
}
