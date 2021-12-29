package com.linbin.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: LinBin
 * @Description:
 * @Date: 2021/11/25 11:30
 * @Version: 1.0
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE-HYSTRIX" , fallback = OrderFallbackService.class)
public interface OrderHystrixService {
    @GetMapping("/payment/ok")
    String paymentOK(@RequestParam("id") Integer id);

    @GetMapping("/payment/timeout")
    String paymentTimeout(@RequestParam("id") Integer id);
}
