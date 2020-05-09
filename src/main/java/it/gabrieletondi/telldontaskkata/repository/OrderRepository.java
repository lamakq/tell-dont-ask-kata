package it.gabrieletondi.telldontaskkata.repository;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.request.OrderApprovalRequest;
import it.gabrieletondi.telldontaskkata.request.OrderShipmentRequest;
import it.gabrieletondi.telldontaskkata.request.SellItemsRequest;

public interface OrderRepository {
    void save(Order order);

    Order getById(int orderId);

    void approveOrder(OrderApprovalRequest orderApprovalRequest);

    void shipOrder(OrderShipmentRequest orderShipmentRequest);

    void createOrder(SellItemsRequest request, ProductCatalog productCatalog);
}
