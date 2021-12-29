package com.linbin.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.linbin.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: LinBin
 * @Description:
 * @Date: 2021/10/21 17:21
 * @Version: 1.0
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/ok")
    public String paymentOK(@RequestParam("id") Integer id){
        String result = paymentService.paymentOK(id);
        log.info("******result：" + result);
        return result;
    }

    @GetMapping("/timeout")
    public String paymentTimeout(@RequestParam("id") Integer id){
        String result = paymentService.paymentTimeout(id);
        log.info("******result：" + result);
        return result;
    }

    @GetMapping("/circuitBreaker")
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback" , commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled" , value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" , value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold" , value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" , value = "60"),//失败率达到多少
            //在10S内10次访问超过60%失败就不能访问
    })
    public String paymentCircuitBreaker(@RequestParam("id") Integer id){
        if (id < 0){
            throw new RuntimeException("******ID不能为负数******");
        }
        String number = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "调用成功！流水号：" + number;
    }
    public String paymentCircuitBreaker_fallback(@RequestParam("id") Integer id){
        return "id不能为负数，请稍后再试！id = " + id;
    }
}
