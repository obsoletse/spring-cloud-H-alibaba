package com.linbin.springcloud.service;

import com.linbin.commom.entity.CommonResult;
import com.linbin.commom.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: LinBin
 * @Description:
 * @Date: 2021/11/22 11:51
 * @Version: 1.0
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")/*微服务名称*/
public interface PaymentFeignService {
    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/timeout")
    String timeOut();
}
