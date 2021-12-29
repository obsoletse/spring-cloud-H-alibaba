package com.linbin.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: LinBin
 * @Description:
 * @Date: 2021/12/22 9:27
 * @Version: 1.0
 */
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator buildRouteLocator(RouteLocatorBuilder builder) {
        //1.获取路由--相当于yml的routes
        RouteLocatorBuilder.Builder routes = builder.routes();
        //2.配置映射
        /*
         * 参数说明：String id, Function<PredicateSpec, Route.AsyncBuilder> fn
         * id：路由id，类似yml routes下的id
         * fn：断言函数格式 r -> r.path("/xxx").uri("aaa");由http://localhost:9527/xxx 映射到地址aaa
         */
        routes.route("baiDu_route", r -> r.path("/guonei").uri("http://news.baidu.com/guonei"))//映射到百度新闻国内的地址
                .route("baiDu_route2", r -> r.path("/guoji").uri("http://news.baidu.com/guoji"))//映射到百度新闻国际的地址
                .route("paymentService", r -> r.path("/payment/**").uri("lb://CLOUD-PAYMENT-SERVICE"))//配置动态路由--支付服务，其中lb是负载协议
                .route("paymentService", r -> r.path("/payment/**").uri("lb://CLOUD-PAYMENT-SERVICE"))//配置动态路由--支付服务，其中lb是负载协议
                .build();
        return routes.build();
    }
}