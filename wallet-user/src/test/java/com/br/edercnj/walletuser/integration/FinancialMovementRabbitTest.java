package com.br.edercnj.walletuser.integration;


import com.br.edercnj.walletuser.mocks.FinancialMovementMock;
import com.br.edercnj.walletuser.application.services.AmqpService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = FinancialMovementRabbitTest.Initializer.class)
@ActiveProfiles("test")
@Slf4j
public class FinancialMovementRabbitTest {

    @ClassRule
    public static GenericContainer rabbit = new GenericContainer("rabbitmq:management-alpine").withExposedPorts(5672, 15672);

    @Autowired
    private AmqpService amqpService;
    @Rule
    public OutputCaptureRule outputCapture = new OutputCaptureRule();


    @Test
    public void send_to_rabbit_should_be_isConsumed() {
        amqpService.sendFinancialMovementToConsumers(FinancialMovementMock.createFinancialMovement());
        await().atMost(5, TimeUnit.SECONDS).until(isMessageConsumed(), is(true));
    }

    private Callable<Boolean> isMessageConsumed() {
        return () -> !outputCapture.toString().isEmpty();
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            val values = TestPropertyValues.of(
                    "spring.rabbitmq.host=" + rabbit.getContainerIpAddress(),
                    "spring.rabbitmq.port=" + rabbit.getMappedPort(5672)
            );
            values.applyTo(configurableApplicationContext);
        }
    }
}
