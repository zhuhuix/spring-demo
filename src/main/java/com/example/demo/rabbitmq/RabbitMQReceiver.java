package com.example.demo.rabbitmq;

import com.example.demo.email.MailManager;
import com.example.demo.register.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * rabbitmq 接收器
 *
 * @author zhuhuix
 * @date 2020-07-14
 */
@Component
public class RabbitMQReceiver {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    @Autowired
    private MailManager mailManager;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 监听消息队列
    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("#{rabbitMQConfig.getExchange()}"),
            key = "#{rabbitMQConfig.getRoutingKey()}",
            value = @Queue("user.queue")))
    public void receiveMessage(Message message) {
        try {
            User user = (User) rabbitTemplate.getMessageConverter().fromMessage(message);
            logger.info("接收到消息:[{}]", user.toString());
            // 发送通知邮件
            if (user != null) {
                sendMail(user);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    // 发送邮件
    private void sendMail(User user) {
        // 发送文本邮件
        String text = "您的邮箱信息已登记!";
        mailManager.sendSimpleMail(user.getEmail(), "用户通知(文本邮件)", text);

        // 发送HTML邮件
        String content = "<html>\n" +
                "<body>\n" +
                "<h3> <font color=\"red\"> " + text + "</font> </h3>\n" +
                "</body>\n" +
                "</html>";
        mailManager.sendHTMLMail(user.getEmail(), "用户通知(HTML邮件)", content);

        // 发送带有附件的邮件
        String attachFilePath = "c:\\csdn-logo.png";
        mailManager.sendAttachmentMail(user.getEmail(), "用户通知(带有附件的邮件)", content, attachFilePath);
    }
}
