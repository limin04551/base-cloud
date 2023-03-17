package com.base.common.core.config;

import com.alibaba.fastjson2.support.spring6.http.converter.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
/**
 * @author limin
 * @description
 * @date 2023/3/16
 */
@ConditionalOnClass(WebMvcConfigurer.class)
public class JsonMessageConverterConfigurer implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //1.转换器
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        // 自定义配置...
        // FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // config.set...
        // converter.setFastJsonConfig(config);
        converters.add(0, converter);



    }
}
