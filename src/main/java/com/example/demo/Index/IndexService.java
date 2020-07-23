package com.example.demo.Index;

import org.springframework.stereotype.Service;

/**
 * 第一个SpringMVC程序--Service层
 *
 * @author zhuhuix
 * @date 2020-07-02
 */
@Service
public class IndexService {
    public String getIndex() {
        return "hello world!!!";
    }
}
