package com.br.edercnj.billpayment.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class BillPaymentOder {
    @Id
    private String id;
    private BillPayment billPayment;
    private FinancialMovement financialMovement;
    private OrderStatus orderStatus;

    public BillPaymentOder( BillPayment billPayment, FinancialMovement financialMovement, OrderStatus orderStatus) {
        this.billPayment = billPayment;
        this.financialMovement = financialMovement;
        this.orderStatus = orderStatus;
    }


    public BillPaymentOder() {
    }
}
