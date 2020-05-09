package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderItem;
import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SellItemsRequest {
    private List<SellItemRequest> requests = new ArrayList<>();
    ProductCatalog productCatalog;

    public SellItemsRequest(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    public List<SellItemRequest> getRequests() {
        return requests;
    }

    void createOrderItems(Order order, ProductCatalog productCatalog) {
        for (SellItemRequest itemRequest : requests) {
            Product product = productCatalog.getByName(itemRequest.getProductName());

            if (product == null) {
                throw new UnknownProductException();
            }

            final OrderItem orderItem = new OrderItem(itemRequest, product);
            order.addItem(orderItem);
        }
    }
}
