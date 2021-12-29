package com.linbin.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author: LinBin
 * @Description:
 * @Date: 2021/11/10 10:53
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentApplication_zk {
    private static final Logger log = LoggerFactory.getLogger(PaymentApplication_zk.class);

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(PaymentApplication_zk.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        //String contextPath = env.getProperty("server.servlet.context-path");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application PaymentApplication_zk is running! Access URLs:\n\t" +
                "Local: http://" + ip + ":" + port + "/\n" +
                "----------------------------------------------------------");
    }
}
