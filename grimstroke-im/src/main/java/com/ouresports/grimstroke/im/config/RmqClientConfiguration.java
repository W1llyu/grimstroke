package com.ouresports.grimstroke.im.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *
 * @author will
 * @date 2018/12/18
 */
@Configuration
public class RmqClientConfiguration {
    public static final String EXCHANGE_NAME = "grimstroke";

    @Bean(name="socket_connect")
    public Queue connectQueue() {
        return new Queue("socket_connect");
    }

    @Bean(name="socket_disconnect")
    public Queue disconnectQueue() {
        return new Queue("socket_disconnect");
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding bindingConnectMessage(@Qualifier("socket_connect") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("im.socket.connect");
    }

    @Bean
    Binding bindingDisconnectMessage(@Qualifier("socket_disconnect") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("im.socket.disconnect");
    }
}
