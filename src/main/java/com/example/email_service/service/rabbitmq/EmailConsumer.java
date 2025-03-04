package com.example.email_service.service.rabbitmq;


import com.example.email_service.model.EmailRequest;

import com.example.email_service.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class EmailConsumer {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @RabbitListener(queues = "email_queue")
    public void consume(EmailRequest emailRequest) {
        System.out.println("📥 Mensagem recebida: " + emailRequest);

        if (emailRequest.getSendAt() == null) {
            System.out.println("O campo 'sendAt' está null! Definindo para horário atual.");
            emailRequest.setSendAt(LocalDateTime.now()); // ou outro valor adequado
        }
        int delay = (int) Duration.between(LocalDateTime.now(), emailRequest.getSendAt()).toMillis();

        if (delay < 0) {
            System.out.println("⏰ O horário de envio já passou. Enviando agora...");
            emailService.sendEmail(emailRequest);
        } else {
            System.out.println("⏳ E-mail agendado para " + emailRequest.getSendAt());
            taskScheduler.schedule(() -> emailService.sendEmail(emailRequest),
                    new java.util.Date(System.currentTimeMillis() + delay));
        }

        LocalDateTime agora = LocalDateTime.now();
        Duration diferenca = Duration.between(emailRequest.getSendAt(), agora);
        System.out.println("⏳ Diferença de tempo: " + diferenca.toMinutes() + " minutos");
    }

}
