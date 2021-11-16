package com.br.edercnj.billpayment.model.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class BillPayment {
    private String username;
    private Float amount;
    private String barCode;
}
