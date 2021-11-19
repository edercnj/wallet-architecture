package com.br.edercnj.walletuser.adapters.outbound.amqp.rabbitmq;

import com.br.edercnj.walletuser.application.ports.outbound.FinancialMovementAmqpPort;
import com.br.edercnj.walletuser.application.domain.entities.FinancialMovement;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FinancialMovementRabbitMQ implements FinancialMovementAmqpPort {

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
                return m;
            });
        } catch (Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
