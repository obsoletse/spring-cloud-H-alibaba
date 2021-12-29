package com.linbin.ribbonRule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: LinBin
 * @Description:
 * @Date: 2021/11/11 10:29
 * @Version: 1.0
 */
@Configuration
public class MyRibbonConfig {
    @Bean
    public IRule myRule(){
        return new MyRibbonRule();
    }
}