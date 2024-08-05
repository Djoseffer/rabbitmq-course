package com.msproposta.service;

import com.msproposta.dto.PropostaRequestDto;
import com.msproposta.dto.PropostaResponseDto;
import com.msproposta.entities.Proposta;
import com.msproposta.mapper.PropostaMapper;
import com.msproposta.repository.PropostaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PropostaService {

    private final PropostaRepository propostaRepository;

    private final NotificacaoRabbitService notificacaoService;

    private final String exchange;

    public PropostaService(PropostaRepository propostaRepository, NotificacaoRabbitService notificacaoService,
                           @Value("${rabbitmq.propostapendente.exchange}")String exchange) {
        this.propostaRepository = propostaRepository;
        this.notificacaoService = notificacaoService;
        this.exchange = exchange;
    }

    @Transactional
    public PropostaResponseDto criar(PropostaRequestDto requestDto) {
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        propostaRepository.save(proposta);
        notificarRabbitMQ(proposta);
        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }

    public List<PropostaResponseDto> obterProposta() {
        return PropostaMapper.INSTANCE.convertListEntitytoToListDto(propostaRepository.findAll());
    }

    public void notificarRabbitMQ(Proposta proposta) {
        try {
        notificacaoService.notificar(proposta, exchange);
        }catch (RuntimeException ex) {
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }
}
