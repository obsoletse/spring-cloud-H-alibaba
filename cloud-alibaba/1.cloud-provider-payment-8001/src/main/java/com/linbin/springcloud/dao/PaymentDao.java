package com.linbin.springcloud.dao;

import com.linbin.commom.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: LinBin
 * @Description:
 * @Date: 2021/10/21 17:07
 * @Version: 1.0
 */
@Mapper
public interface PaymentDao {
    int create(Payment payment);
    Payment getPaymentById(@Param("id") Long id);
}
