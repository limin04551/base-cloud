package com.base.system.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import com.base.common.core.exception.base.BaseException;
import com.base.common.satoken.utils.LoginHelper;
import com.base.system.api.model.LoginUser;
import com.base.system.domain.User;
import com.base.system.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
//    @SaCheckLogin
    @GetMapping("getUserInfo")
    public User getInfo(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
       while(headerNames.hasMoreElements()){
            String value = headerNames.nextElement();//调用nextElement方法获得元素
            System.out.println(request.getHeaders(value).nextElement());
        }

        LoginUser loginUser = LoginHelper.getLoginUser();
        User user = userService.getById(loginUser.getUserId());
        return user;
    }

    @GetMapping("list")
    public List list() {
        return userService.list();
    }


}

