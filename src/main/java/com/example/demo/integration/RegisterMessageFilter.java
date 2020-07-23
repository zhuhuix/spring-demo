package com.example.demo.integration;

import com.example.demo.register.User;
import org.springframework.integration.core.GenericSelector;
import org.springframework.stereotype.Component;

/**
 * Spring Integration过滤器
 *
 * @author zhuhuix
 * @date 2020-07-15
 */
@Component
public class RegisterMessageFilter implements GenericSelector<User> {
    @Override
    public boolean accept(User user) {
        if ((user == null || user.getId() == null || user.getId().equals(0L))) {
            return false;
        } else {
            return true;
        }
    }
}
