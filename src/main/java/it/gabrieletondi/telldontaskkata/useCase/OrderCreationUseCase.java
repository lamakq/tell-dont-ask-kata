package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderItem;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;

import java.math.BigDecimal;
import java.util.ArrayList;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

public class OrderCreationUseCase {
    private final OrderRepository orderRepository;
    private final ProductCatalog productCatalog;

    public OrderCreationUseCase(OrderRepository orderRepository, ProductCatalog productCatalog) {
        this.orderRepository = orderRepository;
        this.productCatalog = productCatalog;
    }

    public void run(SellItemsRequest request) {
        Order order = new Order("EUR");

        for (SellItemRequest itemRequest : request.getRequests()) {
            Product product = productCatalog.getByName(itemRequest.getProductName());

            if (product == null) {
                throw new UnknownProductException();
            }
            else {
                final BigDecimal unitaryTax = product.getUnitaryTax();

                final BigDecimal unitaryTaxedAmount = product.getUnitaryTaxedAmount();

                final BigDecimal taxedAmount = unitaryTaxedAmount.multiply(BigDecimal.valueOf(itemRequest.getQuantity())).setScale(2, HALF_UP);
                final BigDecimal taxAmount = unitaryTax.multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

                final OrderItem orderItem = new OrderItem(product, itemRequest.getQuantity(), taxedAmount, taxAmount);

                order.addOrderItem(orderItem);

            }
        }

        orderRepository.save(order);
    }
}
