package com.br.edercnj.billpayment.repository;

import com.br.edercnj.billpayment.model.entity.BillPaymentOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillPaymentOderRepository extends MongoRepository<BillPaymentOrder, String> {
    Optional<BillPaymentOrder> findByBillPayment_Username(String username);

    @Override
    <S extends BillPaymentOrder> S save(S entity);
}
