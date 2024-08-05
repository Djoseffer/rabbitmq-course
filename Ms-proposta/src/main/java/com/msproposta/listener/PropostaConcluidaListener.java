package com.msproposta.listener;

import com.msproposta.dto.PropostaResponseDto;
import com.msproposta.entities.Proposta;
import com.msproposta.mapper.PropostaMapper;
import com.msproposta.repository.PropostaRepository;
import com.msproposta.service.WebSocketService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropostaConcluidaListener {

    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private WebSocketService webSocketService;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void propostaCocluido(Proposta proposta) {
       propostaRepository.save(proposta);
        PropostaResponseDto propostaDto =  PropostaMapper.INSTANCE.convertEntityToDto(proposta);
        webSocketService.notificar(propostaDto);
    }
}
