package com.notificacao.listener;

import com.notificacao.constante.MensagemConstante;
import com.notificacao.domain.Proposta;
import com.notificacao.service.NotificacaoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropostaPendenteListener {

    @Autowired
    private NotificacaoService notificacaoService;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaPendente(Proposta proposta) {
        String mensagem = String.format(MensagemConstante.PROPOSTA_EM_ANALISE, proposta.getUsuario().getNome());
        notificacaoService.notificar(mensagem);
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void propostaConcluidaMsg(Proposta proposta) {
        String mensagem = String.format(MensagemConstante.PROPOSTA_CONCLUIDA, proposta.getUsuario().getNome());
        notificacaoService.notificar(mensagem);
    }
}
