package com.base.gateway.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 * @description
 * @date 2023/3/3
 */
@RequestMapping("config")
@RestController
@RefreshScope //支持Nacos的动态刷新功能。
public class ConfigController {

}
