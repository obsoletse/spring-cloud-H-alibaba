package com.linbin.springcloud.service.impl;

import com.linbin.commom.entity.Payment;
import com.linbin.springcloud.dao.PaymentDao;
import com.linbin.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: LinBin
 * @Description:
 * @Date: 2021/10/21 17:20
 * @Version: 1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
