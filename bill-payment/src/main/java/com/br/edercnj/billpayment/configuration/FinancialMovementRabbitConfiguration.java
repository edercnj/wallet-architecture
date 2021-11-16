package com.br.edercnj.billpayment.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class FinancialMovementRabbitConfiguration {

    @Value("${amqp.financialMovement.exchange.name}")
    private String financialMovementExchangeName;

    @Value("${amqp.financialMovement.queues.wallet-timeline.name}")
    private String walletTimelineQueue;

    @Value("${amqp.financialMovement.queues.wallet-timeline.deadLetter}")
    private String walletTimelineDeadLetter;

    @Bean
    DirectExchange financialMovementExchange() {
        return new DirectExchange(financialMovementExchangeName);
    }

    @Bean
    Queue walletTimelineQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", financialMovementExchangeName);
        args.put("x-dead-letter-routing-key", walletTimelineDeadLetter);
        return new Queue(walletTimelineQueue, true, false, false);
    }

    @Bean
    Queue walletTimelineDeadLetterQueue() {return new Queue(walletTimelineDeadLetter);}

    @Bean
    public Binding bindingBinQueue() {
        return BindingBuilder
                .bind(walletTimelineQueue())
                .to(financialMovementExchange())
                .with(walletTimelineQueue);
    }

    @Bean
    public Binding bindingDeadLetter() {
        return BindingBuilder
                .bind(walletTimelineDeadLetterQueue())
                .to(financialMovementExchange())
                .with(walletTimelineDeadLetter);
    }
}
