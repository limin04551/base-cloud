package com.base.system.api;

import com.base.common.core.exception.base.BaseException;
import com.base.system.api.model.LoginUser;

/**
 * @author min
 * @description
 * @date 2023/3/8
 */
public interface RemoteUserService {

    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    LoginUser getUserInfo(String username) throws BaseException;

}
