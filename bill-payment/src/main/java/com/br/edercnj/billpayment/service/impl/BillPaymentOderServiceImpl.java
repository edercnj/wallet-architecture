package com.br.edercnj.billpayment.service.impl;

import com.br.edercnj.billpayment.model.entity.*;
import com.br.edercnj.billpayment.repository.BillPaymentOderRepository;
import com.br.edercnj.billpayment.service.BillPaymentOderService;

import java.math.BigDecimal;

public class BillPaymentOderServiceImpl implements BillPaymentOderService {

    private final BillPaymentOderRepository billPaymentOderRepository;

    public BillPaymentOderServiceImpl(BillPaymentOderRepository billPaymentOderRepository) {this.billPaymentOderRepository = billPaymentOderRepository;}

    @Override
    public BillPaymentOder createBillPaymentOder(BillPayment billPayment, User user) {
        FinancialMovement financialMovement = new FinancialMovement(FinancialMovementType.BILL_PAYMENT, user.getId(), BigDecimal.valueOf(billPayment.getAmount()));
        BillPaymentOder billPaymentOder = new BillPaymentOder(billPayment, financialMovement, OrderStatus.PENDING);
        return billPaymentOderRepository.save(billPaymentOder);
    }
}
