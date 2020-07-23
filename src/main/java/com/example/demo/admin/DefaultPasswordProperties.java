package com.example.demo.admin;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 基于SpringMVC框架开发web应用--管理员用户默认密钥
 *
 * @author zhuhuix
 * @date 2020-07-09
 */
@Component
@ConfigurationProperties(prefix = "admin")
public class DefaultPasswordProperties {
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
