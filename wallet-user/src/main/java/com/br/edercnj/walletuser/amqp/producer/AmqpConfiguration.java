package com.br.edercnj.walletuser.amqp.producer;

public interface AmqpConfiguration<T> {
    void producer(T t);
}
