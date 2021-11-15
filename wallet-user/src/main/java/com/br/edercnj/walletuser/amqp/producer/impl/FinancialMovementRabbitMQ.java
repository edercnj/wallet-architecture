package com.br.edercnj.walletuser.amqp.producer.impl;

import com.br.edercnj.walletuser.amqp.producer.AmqpConfiguration;
import com.br.edercnj.walletuser.model.entities.FinancialMovement;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class FinancialMovementRabbitMQ implements AmqpConfiguration<FinancialMovement> {
    private final RabbitTemplate rabbitTemplate;

    @Value("${amqp.financialMovement.exchange.name}")
    private String financialMovementExchangeName;

    @Value("${amqp.financialMovement.queues.wallet-timeline.name}")
    private String walletTimelineQueue;

    public FinancialMovementRabbitMQ(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void producer(FinancialMovement message) {
        try {
            rabbitTemplate.convertAndSend(financialMovementExchangeName, walletTimelineQueue, message, m -> {
                m.getMessageProperties().getHeaders().put("custom-header", message.getMovementIdentifier().toString());
                //m.getMessageProperties().setExpiration("10000");
                return m;
            });
        } catch (Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
