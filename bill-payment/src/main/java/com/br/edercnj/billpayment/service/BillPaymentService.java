package com.br.edercnj.billpayment.service;

import com.br.edercnj.billpayment.model.entity.BillPayment;
import com.br.edercnj.billpayment.model.entity.BillPaymentOrder;

public interface BillPaymentService {

     BillPaymentOrder pay(BillPayment billPayment);
}
