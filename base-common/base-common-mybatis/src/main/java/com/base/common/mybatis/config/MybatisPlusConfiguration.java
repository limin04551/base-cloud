package com.base.common.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author limin
 * @description mybatis-plus配置类(下方注释有插件介绍)
 * @date 2023/3/10
 */
@AutoConfiguration
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan("${mybatis-plus.mapperPackage}")
public class MybatisPlusConfiguration {


}
