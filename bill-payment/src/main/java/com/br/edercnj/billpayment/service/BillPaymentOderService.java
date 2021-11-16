package com.br.edercnj.billpayment.service;

import com.br.edercnj.billpayment.model.entity.BillPayment;
import com.br.edercnj.billpayment.model.entity.BillPaymentOder;
import com.br.edercnj.billpayment.model.entity.FinancialMovement;
import com.br.edercnj.billpayment.model.entity.User;

public interface BillPaymentOderService {
    BillPaymentOder createBillPaymentOder(BillPayment billPayment,  User user);
}
