package com.msproposta.service;

import com.msproposta.entities.Proposta;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificacaoRabbitService {

    private RabbitTemplate rabbitTemplate;

    public void notificar(Proposta proposta, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }
}
