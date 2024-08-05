package com.analisedecredito.listener;

import com.analisedecredito.domain.Proposta;
import com.analisedecredito.service.AnaliseCreditoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropostaPendenteListener {

    @Autowired
    private AnaliseCreditoService analiseCreditoService;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaPendente(Proposta proposta) {
        analiseCreditoService.analisar(proposta);
    }
}
