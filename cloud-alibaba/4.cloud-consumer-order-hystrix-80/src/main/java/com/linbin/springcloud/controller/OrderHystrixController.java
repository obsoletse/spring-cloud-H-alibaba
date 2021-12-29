package com.linbin.springcloud.controller;

import cn.hutool.core.io.FileUtil;
import com.linbin.springcloud.service.OrderHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LinBin
 * @Description:
 * @Date: 2021/11/25 11:32
 * @Version: 1.0
 */
@RestController
@RequestMapping("/consumer")
@Slf4j
public class OrderHystrixController {

    @Autowired
    private OrderHystrixService orderHystrixService;

    @GetMapping("/ok")
    public String paymentOK(@RequestParam("id") Integer id){
        return orderHystrixService.paymentOK(id);
    }

   /* @HystrixCommand(fallbackMethod = "timeoutFallBack",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" , value = "1500")
    })*/
    @GetMapping("/timeout")
    public String paymentTimeout(@RequestParam("id") Integer id){
        return orderHystrixService.paymentTimeout(id);
    }

    public String timeoutFallBack(Integer id) {
        return id + ",我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }
}