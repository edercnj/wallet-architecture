package com.br.edercnj.billpayment.service.impl;

import com.br.edercnj.billpayment.model.entity.BillPayment;
import com.br.edercnj.billpayment.model.entity.BillPaymentOrder;
import com.br.edercnj.billpayment.model.entity.User;
import com.br.edercnj.billpayment.service.AmqpService;
import com.br.edercnj.billpayment.service.BillPaymentOrderService;
import com.br.edercnj.billpayment.service.BillPaymentService;
import com.br.edercnj.billpayment.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class BillPaymentServiceImpl implements BillPaymentService {

    private final UserService userService;
    private final BillPaymentOrderService billPaymentOrderService;
    private final AmqpService amqpService;

    public BillPaymentServiceImpl(UserService userService, BillPaymentOrderService billPaymentOrderService, AmqpService amqpService) {
        this.userService = userService;
        this.billPaymentOrderService = billPaymentOrderService;
        this.amqpService = amqpService;
    }

    @Override
    public BillPaymentOrder pay(BillPayment billPayment) {
        User user = userService.findUserByUsername(billPayment.getUsername());
        BillPaymentOrder billPaymentOrder = billPaymentOrderService.createBillPaymentOder(billPayment, user);
        amqpService.sendFinancialMovementToConsumers(billPaymentOrder.getFinancialMovement());
        return billPaymentOrder;
    }
}
