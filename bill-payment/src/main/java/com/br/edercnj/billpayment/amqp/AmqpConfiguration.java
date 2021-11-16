package com.br.edercnj.billpayment.amqp;

public interface AmqpConfiguration<T> {
    void producer(T t);
}
