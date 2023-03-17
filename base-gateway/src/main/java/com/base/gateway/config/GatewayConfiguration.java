package com.base.gateway.config;

import com.base.gateway.handler.GlobalGatewayExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author limin
 * @description 网关配置类
 * @date 2023/3/17
 */

@Configuration(proxyBeanMethods = false)
public class GatewayConfiguration {

    @Bean
    public GlobalGatewayExceptionHandler globalExceptionHandler() {
        return new GlobalGatewayExceptionHandler();
    }
}
