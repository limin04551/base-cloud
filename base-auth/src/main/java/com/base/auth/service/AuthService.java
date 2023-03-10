package com.base.auth.service;


import com.base.common.core.exception.base.BaseException;
import com.base.system.api.RemoteUserService;
import com.base.system.api.model.LoginUser;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;


/**
 * @author limin
 */
@Service
public class AuthService {

    @DubboReference
    private RemoteUserService remoteUserService;

    /**
     * 登录
     */
    public LoginUser login(String username) {
        LoginUser userInfo = remoteUserService.getUserInfo(username);
        return userInfo;
//        try {
//            LoginUser userInfo = remoteUserService.getUserInfo(username);
//            return userInfo;
//        }catch (BaseException e){
//            e.printStackTrace();
//            throw e ;
//
//        }
    }


}
