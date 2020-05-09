package it.gabrieletondi.telldontaskkata.repository;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;
import it.gabrieletondi.telldontaskkata.useCase.OrderShipmentRequest;

public interface OrderRepository {
    void save(Order order);

    Order getById(int orderId);

    void approveOrder(OrderApprovalRequest orderApprovalRequest);

    void shipOrder(OrderShipmentRequest orderShipmentRequest);

}
