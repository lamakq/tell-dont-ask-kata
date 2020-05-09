package it.gabrieletondi.telldontaskkata.request;

public class OrderShipmentRequest {
    private int orderId;

    public OrderShipmentRequest(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }
}
