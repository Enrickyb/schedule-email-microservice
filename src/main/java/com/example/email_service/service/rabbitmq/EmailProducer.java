package com.example.email_service.service.rabbitmq;

import com.example.email_service.model.EmailRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

    @Service
    public class EmailProducer {

        private final RabbitTemplate rabbitTemplate;

        private static final String EXCHANGE_NAME = "email_exchange";
        private static final String ROUTING_KEY = "email_routing";




        public EmailProducer(RabbitTemplate rabbitTemplate) {
            this.rabbitTemplate = rabbitTemplate;
        }

        public void sendToQueue(EmailRequest request) {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, request);
            System.out.println("📩 E-mail agendado para " + request.getSendAt());
        }
    }