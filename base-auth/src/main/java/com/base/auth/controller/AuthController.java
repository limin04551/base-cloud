package com.base.auth.controller;

import com.base.auth.service.AuthService;
import com.base.common.core.exception.base.BaseException;
import com.base.system.api.model.LoginUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 * @description
 * @date 2023/3/8
 */
@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("login")
    public LoginUser login(String username) {
      return authService.login(username);
    }

    @GetMapping("login2")
    public LoginUser login2(String username) {
        if(true){
            throw new BaseException("测试错误");
        }
        return authService.login(username);
    }

}
