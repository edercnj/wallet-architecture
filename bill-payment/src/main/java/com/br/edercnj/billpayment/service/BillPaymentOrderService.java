package com.br.edercnj.billpayment.service;

import com.br.edercnj.billpayment.model.entity.BillPayment;
import com.br.edercnj.billpayment.model.entity.BillPaymentOrder;
import com.br.edercnj.billpayment.model.entity.User;

public interface BillPaymentOrderService {
    BillPaymentOrder createBillPaymentOder(BillPayment billPayment, User user);
}
