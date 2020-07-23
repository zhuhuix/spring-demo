package com.example.demo.integration;

import com.example.demo.register.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

/**
 * Spring Integration消息发送
 *
 * @author zhuhuix
 * @date 2020-07-15
 */
@Component
public class RegisterRabbitMQSendHandler implements GenericHandler<User> {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Object handle(User user, MessageHeaders messageHeaders) {
        rabbitTemplate.convertAndSend(user);
        return null;
    }
}
