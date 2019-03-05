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
    public static final String EXCHANGE_NAME = "lux_default";

    @Bean(name="join_room")
    public Queue connectQueue() {
        return new Queue("join_room");
    }

    @Bean(name="leave_room")
    public Queue disconnectQueue() {
        return new Queue("leave_room");
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding bindingConnectMessage(@Qualifier("join_room") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("socket.joinRoom.callback");
    }

    @Bean
    Binding bindingDisconnectMessage(@Qualifier("leave_room") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("socket.leaveRoom.callback");
    }
}
