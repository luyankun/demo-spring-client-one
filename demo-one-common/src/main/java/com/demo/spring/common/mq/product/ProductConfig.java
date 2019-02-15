package com.demo.spring.common.mq.product;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@EnableRabbit
@Configuration
public class ProductConfig {

    @Value(value = "${rabbit.product.host}")
    private String host;
    @Value(value = "${rabbit.product.port}")
    private int port;
    @Value(value = "${rabbit.product.username}")
    private String username;
    @Value(value = "${rabbit.product.password}")
    private String password;
    @Value(value = "${rabbit.product.virtualHost}")
    private String virtualHost;
    @Value(value = "${rabbit.product.topic.exchange.name}")
    private String productTopicExchangeName;
    @Value(value = "${rabbit.product.queue.name}")
    private String productQueue;
    @Value(value = "${rabbit.product.customer.request.routing}")
    private String productToCustomerRoutingKey;


    @Bean(value = "productConnectionFactory")
    public ConnectionFactory productConnectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
        return factory;
    }


    @Bean(value = "productRabbitAdmin")
    public RabbitAdmin productRabbitAdmin(
            @Qualifier(value = "productConnectionFactory") ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }


    @Bean(value = "productRabbitTemplate")
    public RabbitTemplate productRabbitTemplate(
            @Qualifier(value = "productConnectionFactory") ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean(value = "productTopicExchange")
    public TopicExchange productTopicExchange() {
        return new TopicExchange(productTopicExchangeName, true, false);
    }

    @Bean(value = "productQueue")
    public Queue productQueue() {
        return new Queue(productQueue, true);
    }

    @Bean
    public RabbitListenerContainerFactory productRabbitListenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier(value = "productConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public RabbitAdmin registryRoutes(
            @Qualifier(value = "productRabbitAdmin") RabbitAdmin rabbitAdmin,
            @Qualifier(value = "productQueue") Queue queue,
            @Qualifier(value = "productTopicExchange") Exchange exchange) {
        Binding productToCustomerRequest = BindingBuilder.bind(queue).to(exchange)
                .with(productToCustomerRoutingKey).noargs();
        rabbitAdmin.declareBinding(productToCustomerRequest);
        return rabbitAdmin;
    }

}
