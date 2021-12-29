package com.linbin.springcloud.controller;

import com.linbin.commom.entity.CommonResult;
import com.linbin.commom.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: LinBin
 * @Description:
 * @Date: 2021/11/8 9:44
 * @Version: 1.0
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private static final String SERVICE_NAME = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/create")
    public ResponseEntity<CommonResult> create(@RequestParam("serial") String serial) {
        Payment payment = new Payment();
        payment.setSerial(serial);
        return restTemplate.postForEntity(SERVICE_NAME + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/get")
    public ResponseEntity<CommonResult> queryOne(@RequestParam("id") Long id) {
        return restTemplate.getForEntity(SERVICE_NAME + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/getEntity")
    public ResponseEntity<CommonResult> queryOneEntity(@RequestParam("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(SERVICE_NAME + "/payment/get/" + id, CommonResult.class);
        HttpStatus statusCode = entity.getStatusCode();
        int statusCodeValue = entity.getStatusCodeValue();
        HttpHeaders headers = entity.getHeaders();
        CommonResult body = entity.getBody();
        System.out.println("*******getForEntity******");
        System.out.println("statusCode:" + statusCode);
        System.out.println("statusCodeValue:" + statusCodeValue);
        System.out.println("headers:" + headers);
        System.out.println("body:" + body);
        System.out.println("*******getForObject******");
        CommonResult object = restTemplate.getForObject(SERVICE_NAME + "/payment/get/" + id, CommonResult.class);
        System.out.println("object:" + object);
        return entity;
    }
}