package com.base.common.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author limin
 * @description 解决返回String报错
 * @date 2023/3/13
 */
@ConditionalOnClass(WebMvcConfigurer.class)
public class WebResponseConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(e -> e.getClass().isAssignableFrom(StringHttpMessageConverter.class));
    }
}
