package com.example.demo.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq 配置类
 *
 * @author zhuhuix
 * @date 2020-07-14
 */
@Configuration(value = "rabbitMQConfig")
public class RabbitMQConfig {

    // 获取exchange和routing-key定义
    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingKey;

    public String getExchange() {
        return exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    // 自定义消息转换器
    @Bean
    public MessageConverter messageConverter() {

        return new SimpleMessageConverter() {
            @Override
            protected Message createMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
                Message message = super.createMessage(object, messageProperties);
                return message;
            }
        };

    }

}
