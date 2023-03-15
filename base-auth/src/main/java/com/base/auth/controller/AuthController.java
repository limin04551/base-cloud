package com.base.auth.controller;

import com.base.auth.model.LoginBody;
import com.base.auth.service.AuthService;
import com.base.common.core.exception.base.BaseException;
import com.base.system.api.model.LoginUser;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("login")
    public String login(@Validated @RequestBody LoginBody loginBody) {
        // 用户登录
        return authService.login(loginBody);
    }


}
