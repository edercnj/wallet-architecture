package com.br.edercnj.billpayment.repository;

import com.br.edercnj.billpayment.model.entity.BillPaymentOder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillPaymentOderRepository extends MongoRepository<BillPaymentOder, String> {
    Optional<BillPaymentOder> findByBillPayment_Username(String username);

    @Override
    <S extends BillPaymentOder> S save(S entity);
}
