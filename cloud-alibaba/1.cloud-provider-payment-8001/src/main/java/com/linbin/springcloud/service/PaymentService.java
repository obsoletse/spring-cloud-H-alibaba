package com.linbin.springcloud.service;

import com.linbin.commom.entity.Payment;

/**
 * @Author: LinBin
 * @Description:
 * @Date: 2021/10/21 17:19
 * @Version: 1.0
 */
public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(Long id);
}