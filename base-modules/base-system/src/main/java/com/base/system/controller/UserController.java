package com.base.system.controller;


import com.base.common.core.exception.base.BaseException;
import com.base.system.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jiuyue
 * @since 2023-03-06
 */
@RestController
@RequestMapping("/system/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("test")
    public String test() {
        if (true) {
            throw new BaseException("测试异常");
        }
        return "test";
    }

    @GetMapping("list")
    public List list() {
        return userService.list();
    }


}

