package com.linbin.springcloud.controller;


import com.linbin.commom.entity.CommonResult;
import com.linbin.commom.entity.Payment;
import com.linbin.springcloud.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String port;

    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入操作返回结果:{}*****", result);
        if (result > 0) {
            return new CommonResult<>(200, "port:" + port + " 插入数据库成功", result);
        } else {
            return new CommonResult<>(444, "port:" + port + " 插入数据库失败");
        }
    }

    @GetMapping(value = "/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果:{}*****", payment);
        if (payment != null) {
            return new CommonResult<>(200, "port:" + port + " 查询成功", payment);
        } else {
            return new CommonResult<>(444, "port:" + port + " 没有对应记录,查询ID: " + id, null);
        }
    }

    @GetMapping(value = "/discovery")
    public CommonResult<List> discovery() {
        List<String> services = discoveryClient.getServices();
        log.info("*****服务清单:{}*****", services);
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("服务名：{} 主机：{} 端口：{} URI:{}", instance.getServiceId(), instance.getHost(), instance.getPort(), instance.getUri());
        }
        return new CommonResult<>(200, "获取服务列表成功！", services);
    }

    @GetMapping(value = "timeout")
    public String TimeOut() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return port;
    }
}
