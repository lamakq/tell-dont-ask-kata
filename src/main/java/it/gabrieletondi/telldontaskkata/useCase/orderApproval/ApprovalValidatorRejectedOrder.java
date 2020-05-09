package it.gabrieletondi.telldontaskkata.useCase.orderApproval;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;

public class ApprovalValidatorRejectedOrder implements ApprovalValidator {
    @Override
    public void validate(OrderApprovalRequest request, Order order) throws OrderApprovalException {
        if (request.isApproved() && order.getStatus().equals(OrderStatus.REJECTED)) {
            throw new RejectedOrderCannotBeApprovedException();
        }
    }
}
