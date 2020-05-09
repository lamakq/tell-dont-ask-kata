package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;

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

    Order createOrder() {
        Order order = new Order();
        for (SellItemRequest itemRequest : requests) {
            Product product = productCatalog.getByName(itemRequest.getProductName());
            assertProductNotEmpty(product);

            order.addOrderItem(itemRequest, product);
        }
        return order;
    }

    private void assertProductNotEmpty(Product product) {
        if (product == null) {
            throw new UnknownProductException();
        }
    }
}
