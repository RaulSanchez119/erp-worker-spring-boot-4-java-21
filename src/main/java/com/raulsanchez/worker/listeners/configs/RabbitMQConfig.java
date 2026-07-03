package com.raulsanchez.worker.listeners.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.json.JsonMapper;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE    = "erp.events";
    public static final String QUEUE       = "erp.product.created";
    public static final String ROUTING_KEY = "product.created";

    @Bean
    public TopicExchange getExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue getQueue() {
        return new Queue(QUEUE);
    }

    @Bean
    public Binding getBinding(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }

    @Bean
    public JacksonJsonMessageConverter messageConverter(JsonMapper jsonMapper) {
        return new JacksonJsonMessageConverter(jsonMapper);
    }


    @Bean
    public SimpleRabbitListenerContainerFactory connectionFactory(
            ConnectionFactory connectionFactory, JacksonJsonMessageConverter converter) {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(converter);
        factory.setDefaultRequeueRejected(false);

        return factory;

    }

}