package com.example.demo.admin;

import com.example.demo.register.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 基于SpringMVC框架开发web应用--管理员注册控制器
 *
 * @author zhuhuix
 * @date 2020-07-08
 * @date 2020-07-09 注册密码为空时使用自定义的默认密码属性
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private DefaultPasswordProperties defaultPasswordProperties;

    // 管理注册页
    @GetMapping
    public ModelAndView registerForm(Model model) {
        model.addAttribute("admin", new Admin());
        return new ModelAndView("registration", "adminModel", model);
    }

    // 提交注册信息
    @PostMapping("/register")
    public ModelAndView save(Admin admin) {
        // 如果注册密码为空，则赋值默认密码
        if (StringUtils.isEmpty(admin.getPassword())) {
            admin.setUserPassword(defaultPasswordProperties.getPassword());
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        admin.setUserPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        if (adminService.save(admin) != null) {
            return new ModelAndView("redirect:/login ");
        } else {
            return null;
        }
    }
}
