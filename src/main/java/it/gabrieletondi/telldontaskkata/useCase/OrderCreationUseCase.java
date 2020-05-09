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

    public void run(SellItemsRequest request) throws UnknownProductException {
        Order order = new Order("EUR");

        for (SellItemRequest itemRequest : request.getRequests()) {
            Product product = productCatalog.getByName(itemRequest.getProductName());

            // unitaryTax = price/100 * taxPercent
            final BigDecimal unitaryTax = product.getUnitaryTax();
            // uTAmt = (price + uTax)
            final BigDecimal unitaryTaxedAmount = product.getPrice().add(unitaryTax).setScale(2, HALF_UP);
            // taxedAMount = uTAmt * quantity
            final BigDecimal taxedAmount = unitaryTaxedAmount.multiply(BigDecimal.valueOf(itemRequest.getQuantity())).setScale(2, HALF_UP);
            // taxAmount = uTax * quantity;
            final BigDecimal taxAmount = unitaryTax.multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            final OrderItem orderItem = new OrderItem(product, itemRequest.getQuantity(), taxedAmount, taxAmount);
            order.addItem(orderItem);

        }

        orderRepository.save(order);
    }
}
