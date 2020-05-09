package it.gabrieletondi.telldontaskkata.request;

public class OrderApprovalRequest {
    private int orderId;
    private boolean approved;

    public OrderApprovalRequest(int orderId, boolean approved) {
        this.orderId = orderId;
        this.approved = approved;
    }

    public int getOrderId() {
        return orderId;
    }

    public boolean isApproved() {
        return approved;
    }
}
