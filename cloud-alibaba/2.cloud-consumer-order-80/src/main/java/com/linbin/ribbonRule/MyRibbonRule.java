package com.linbin.ribbonRule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * @Author: LinBin
 * @Description:
 * @Date: 2021/11/11 11:00
 * @Version: 1.0
 */
public class MyRibbonRule extends AbstractLoadBalancerRule {
    //设计想法：total = 5; currentIndex + 1 超过机器，currentIndex归0
    private int total = 0;    //总共被调用的次数，目前要求每台被调用5次
    private int currentIndex = 0;//当前提供服务的机器号

    /**
     * 选择服务
     * @param key
     * @return
     */
    private Server choose(ILoadBalancer lb, Object key) {
        //lb不会为空
        if (lb == null) {
            return null;
        }

        //定义负载获取的server对象
        Server server = null;

        while (server == null) {
            //线程中断返回空
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();//获取活着的服务列表
            List<Server> allList = lb.getAllServers();//获取所有服务列表

            //还没服务，返回空
            int serverCount = allList.size();
            if (serverCount == 0) {
                return null;
            }
            //自定义获取server
            if (total < 5){
                server = upList.get(currentIndex);
                total ++;
            }else {
                total = 0 ;
                currentIndex ++ ;
                if (currentIndex >= upList.size()){
                    currentIndex = 0;
                }
            }
            if (server == null) {
                Thread.yield();//中断线程。过阵子再调用
                continue;
            }
            //获取了符合条件的server
            if (server.isAlive()) {
                return server;
            }
            server = null;
            Thread.yield();
        }
        return server;
    }

    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }


    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}
