package com.br.edercnj.billpayment.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class BillPayment {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private Float amount;
    private String barCode;
}
