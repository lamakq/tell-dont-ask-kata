package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.request.OrderShipmentRequest;
import it.gabrieletondi.telldontaskkata.service.ShipmentService;

public class OrderShipmentUseCase {
    private final OrderRepository orderRepository;

    public OrderShipmentUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void run(OrderShipmentRequest request) {
        orderRepository.shipOrder(request);
    }
}
