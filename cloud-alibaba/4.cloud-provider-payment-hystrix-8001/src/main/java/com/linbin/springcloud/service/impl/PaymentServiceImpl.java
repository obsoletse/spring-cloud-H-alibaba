package com.linbin.springcloud.service.impl;

import com.linbin.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: LinBin
 * @Description:
 * @Date: 2021/10/21 17:20
 * @Version: 1.0
 */
@Service
@DefaultProperties(defaultFallback = "GlobalFallback")
public class PaymentServiceImpl implements PaymentService {

    @Override
    @HystrixCommand
    public String paymentOK(Integer id) {
        int a = 10 / 0;//异常降级
        return "线程池：" + Thread.currentThread().getName() + " 执行paymentOk，ID = " + id + "/t" + "O(∩_∩)O哈哈~";
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentTimeoutFallBack",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentTimeout(Integer id) {
        //int a = 10 / 0;//异常降级
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " 执行paymentTimeout，ID = " + id + "/t" + "O(∩_∩)O哈哈~，耗时3S";
    }

    public String paymentTimeoutFallBack(Integer id) {
        return id + "/(ㄒoㄒ)/调用支付接口超时或异常，当前线程池名字：" + Thread.currentThread().getName();
    }

    public String GlobalFallback(){
        return "/(ㄒoㄒ)GlobalFallBack调用支付接口超时或异常，当前线程池名字：" + Thread.currentThread().getName();
    }
}