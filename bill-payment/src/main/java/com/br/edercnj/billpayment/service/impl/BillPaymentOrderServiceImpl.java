package com.br.edercnj.billpayment.service.impl;

import com.br.edercnj.billpayment.model.entity.*;
import com.br.edercnj.billpayment.repository.BillPaymentOderRepository;
import com.br.edercnj.billpayment.service.BillPaymentOrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BillPaymentOrderServiceImpl implements BillPaymentOrderService {

    private final BillPaymentOderRepository billPaymentOderRepository;

    public BillPaymentOrderServiceImpl(BillPaymentOderRepository billPaymentOderRepository) {this.billPaymentOderRepository = billPaymentOderRepository;}

    @Override
    public BillPaymentOrder createBillPaymentOder(BillPayment billPayment, User user) {
        FinancialMovement financialMovement = new FinancialMovement(FinancialMovementType.BILL_PAYMENT, user.getId(), BigDecimal.valueOf(billPayment.getAmount()));
        BillPaymentOrder billPaymentOrder = new BillPaymentOrder(billPayment, financialMovement, OrderStatus.PENDING);
        return billPaymentOderRepository.save(billPaymentOrder);
    }
}
