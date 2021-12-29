package com.linbin.springcloud;

import com.linbin.ribbonRule.MyRibbonConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author: LinBin
 * @Description:
 * @Date: 2021/11/8 9:42
 * @Version: 1.0
 */
@Slf4j
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MyRibbonConfig.class)
public class OrderApplication_80 {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(OrderApplication_80.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application OrderApplication_80 is running! Access URLs:\n\t" +
                "Local: http://" + ip + ":" + port + "/\n" +
                "----------------------------------------------------------");
    }
}