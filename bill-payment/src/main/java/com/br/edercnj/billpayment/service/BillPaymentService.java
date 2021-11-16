package com.br.edercnj.billpayment.service;

import com.br.edercnj.billpayment.model.entity.BillPayment;
import com.br.edercnj.billpayment.model.entity.FinancialMovement;

public interface BillPaymentService {

     FinancialMovement pay(BillPayment billPayment);
}
