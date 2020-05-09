package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;

import static java.math.BigDecimal.valueOf;

public class OrderCreationUseCase {
    private final OrderRepository orderRepository;

    public OrderCreationUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void run(SellItemsRequest request) {
        Order order = request.createOrder();
        orderRepository.save(order);
    }

}
