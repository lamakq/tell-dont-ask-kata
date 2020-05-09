package it.gabrieletondi.telldontaskkata.useCase.orderApproval;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;

public interface ApprovalValidator {
    void validate(OrderApprovalRequest request, Order order) throws OrderApprovalException;
}
