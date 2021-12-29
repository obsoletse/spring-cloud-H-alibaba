package com.linbin.springcloud.service;

/**
 * @Author: LinBin
 * @Description:
 * @Date: 2021/10/21 17:19
 * @Version: 1.0
 */
public interface PaymentService {
    String paymentOK(Integer id);
    String paymentTimeout(Integer id);
}