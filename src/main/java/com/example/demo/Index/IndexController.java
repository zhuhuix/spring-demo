package com.example.demo.Index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 第一个SpringMVC程序--Controller层
 *
 * @author zhuhuix
 * @date 2020-07-02
 */
@Controller
public class IndexController {
    @Autowired
    private IndexService indexService;

    @GetMapping("/")
    public String index(Model model) {
        String index = indexService.getIndex();
        model.addAttribute("index", index);
        return "index";
    }
}
