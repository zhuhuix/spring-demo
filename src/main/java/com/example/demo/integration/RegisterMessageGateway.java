package com.example.demo.integration;

import com.example.demo.register.User;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

/**
 * Spring Integration网关入口
 *
 * @author zhuhuix
 * @date 2020-07-15
 */
@Component
@MessagingGateway(defaultRequestChannel = "registerChannel")
public interface RegisterMessageGateway {
    /**
     * 注册通知流程
     *
     * @param user 用户信息
     */
    void registerMessageFlow(User user);
}
