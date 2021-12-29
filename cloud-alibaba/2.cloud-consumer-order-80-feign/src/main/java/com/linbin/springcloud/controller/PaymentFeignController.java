package com.linbin.springcloud.controller;

import com.linbin.commom.entity.CommonResult;
import com.linbin.commom.entity.Payment;
import com.linbin.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LinBin
 * @Description:
 * @Date: 2021/11/22 11:55
 * @Version: 1.0
 */
@RestController
@RequestMapping("/consumer")
public class PaymentFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/timeout")
    public String TimeOut(){
        return paymentFeignService.timeOut();
    }
}
