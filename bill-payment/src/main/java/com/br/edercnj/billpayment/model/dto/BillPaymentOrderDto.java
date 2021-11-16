package com.br.edercnj.billpayment.model.dto;

import com.br.edercnj.billpayment.model.entity.BillPayment;
import com.br.edercnj.billpayment.model.entity.OrderStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class BillPaymentOrderDto {
    @Id
    private String id;
    private BillPayment billPayment;
    private OrderStatus orderStatus;
}
