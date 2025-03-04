package com.example.email_service.controller;

import com.example.email_service.model.EmailRequest;
import com.example.email_service.service.rabbitmq.EmailProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final com.example.email_service.service.rabbitmq.EmailProducer emailProducer;

    public EmailController(EmailProducer emailProducer) {
        this.emailProducer = emailProducer;
    }

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest request) {
        emailProducer.sendToQueue(request);
        return "✅ E-mail enfileirado para " + request.getTo();
    }



    @PostMapping("/schedule")
    public String scheduleEmail(@RequestBody EmailRequest emailRequest) {
        emailProducer.sendToQueue(emailRequest);
        return "📩 E-mail agendado com sucesso para " + emailRequest.getSendAt();
    }
}