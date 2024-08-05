package com.msproposta.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.propostapendente.exchange}")
    private String exchangepropostapendente;

    @Value("${rabbitmq.propostaconcluida.exchange}")
    private String exchangepropostaconcluida;

    @Bean
    public Queue criarFilaPropostaMsAnalisePendente() {
        return QueueBuilder.durable("proposta-pendente.ms-analise-de-credito")
                .deadLetterExchange("proposta-pendente-dlx.ex").build();
    }

    @Bean
    public Queue criarFilaPropostaPendenteMsNotificao(){
        return QueueBuilder.durable("proposta-pendente-ms-notificacao").build();
    }

    @Bean
    public Queue criarFilaPropostaConcluidaMsProposta(){
        return QueueBuilder.durable("proposta-concluida-ms-proposta").build();
    }

    @Bean
    public Queue criarFilaPropostaConcluidaMsNotificao(){
        return QueueBuilder.durable("proposta-concluida-ms-notificacao").build();
    }

    @Bean
    public RabbitAdmin criarRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializarAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public FanoutExchange criarFanoutExchangePropostaPendente() {
        return ExchangeBuilder.fanoutExchange(exchangepropostapendente).build();
    }

    @Bean
    public Queue criarFilaPropostaPendenteDlq(){
        return QueueBuilder.durable("proposta-pendente.dlq").build();
    }

    @Bean
    public FanoutExchange deadLetterExchange() {
        return ExchangeBuilder.fanoutExchange("proposta-pendente-dlx.ex").build();
    }

    @Bean
    public Binding criarBinding() {
        return BindingBuilder.bind(criarFilaPropostaPendenteDlq())
                .to(deadLetterExchange());
    }

    @Bean
    public FanoutExchange criarFanoutExchangePropostaConcluida() {
        return ExchangeBuilder.fanoutExchange(exchangepropostaconcluida).build();
    }

    @Bean
    public Binding criarBindingPropostaPendenteMsAnaliseDeCredito() {
        return BindingBuilder.bind(criarFilaPropostaMsAnalisePendente())
                .to(criarFanoutExchangePropostaPendente());
    }

    @Bean
    public Binding criarBindingPropostaConcluidaMsPropostaApp() {
        return BindingBuilder.bind(criarFilaPropostaConcluidaMsProposta())
                .to(criarFanoutExchangePropostaConcluida());
    }

    @Bean
    public Binding criarBindingPropostaConcluidaMsNotificacao() {
        return BindingBuilder.bind(criarFilaPropostaConcluidaMsNotificao())
                .to(criarFanoutExchangePropostaConcluida());
    }

    @Bean
    public Binding criarBindingPropostaPendenteMs() {
        return BindingBuilder.bind(criarFilaPropostaMsAnalisePendente())
                .to(criarFanoutExchangePropostaPendente());
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
