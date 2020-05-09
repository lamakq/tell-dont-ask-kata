package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderItem;
import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

            int roundingScale = 2;
            RoundingMode roundingMode = HALF_UP;
            int itemRequestQuantity = itemRequest.getQuantity();

            final BigDecimal unitaryTax = product.getUnitaryTax().setScale(roundingScale, roundingMode);
            final BigDecimal unitaryTaxedAmount = product.getUnitaryTaxedAmount(unitaryTax).setScale(roundingScale, roundingMode);
            final BigDecimal taxedAmount = unitaryTaxedAmount.multiply(BigDecimal.valueOf(itemRequestQuantity)).setScale(roundingScale, roundingMode);
            final BigDecimal tax = unitaryTax.multiply(BigDecimal.valueOf(itemRequestQuantity));

            final OrderItem orderItem = new OrderItem(product, itemRequestQuantity, tax, taxedAmount);
            order.getItems().add(orderItem);

            order.setTotal(order.getTotal().add(taxedAmount));
            order.setTax(order.getTax().add(tax));
        }

        orderRepository.save(order);
    }

}
