package com.example.demo.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 基于SpringMVC框架开发web应用--管理员服务层
 *
 * @author zhuhuix
 * @date 2020-07-08
 */
@Service
public class AdminService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(Logger.class);
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUserName(s);
        if (admin == null) {
            logger.error("管理员" + s + "未找到");
            throw new UsernameNotFoundException("User " + s + "not found");
        }
        return admin;
    }

    // 保存管理员
    public Admin save(Admin admin){
       return adminRepository.save(admin);
    }
}
