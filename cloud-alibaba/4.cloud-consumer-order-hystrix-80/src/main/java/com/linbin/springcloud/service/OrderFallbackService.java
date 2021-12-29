package com.linbin.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Author: LinBin
 * @Description:
 * @Date: 2021/12/2 11:06
 * @Version: 1.0
 */
@Component
public class OrderFallbackService implements OrderHystrixService {
    @Override
    public String paymentOK(Integer id) {
        return null;
    }

    @Override
    public String paymentTimeout(Integer id) {
        return "糙。怎么挂了";
    }
}
