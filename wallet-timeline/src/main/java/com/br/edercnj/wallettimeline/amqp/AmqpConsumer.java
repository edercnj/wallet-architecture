package com.br.edercnj.wallettimeline.amqp;

public interface AmqpConsumer<T> {
    void consumer(T t);
}
