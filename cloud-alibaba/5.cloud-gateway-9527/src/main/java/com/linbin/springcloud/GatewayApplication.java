package com.linbin.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author: LinBin
 * @Description:
 * @Date: 2021/12/21 9:40
 * @Version: 1.0
 */
@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class GatewayApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(GatewayApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        //String contextPath = env.getProperty("server.servlet.context-path");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application GatewayApplication is running! Access URLs:\n\t" +
                "Local: http://" + ip + ":" + port + "/\n" +
                "----------------------------------------------------------");
    }
}