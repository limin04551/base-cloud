package com.base.auth.service;


//import cn.hutool.core.util.ObjectUtil;
//import cn.hutool.crypto.digest.BCrypt;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.base.auth.config.UserPasswordConfig;
import com.base.auth.model.LoginBody;
import com.base.common.redis.utils.RedisUtils;
import com.base.common.core.constant.CacheConstants;
import com.base.common.core.constant.Constants;
import com.base.common.core.enums.DeviceType;
import com.base.common.core.enums.LoginType;
import com.base.common.core.exception.base.BaseException;
import com.base.common.satoken.utils.LoginHelper;
import com.base.system.api.RemoteUserService;
import com.base.system.api.model.LoginUser;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.function.Supplier;


/**
 * @author limin
 */
@Service
public class AuthService {

    @DubboReference
    private RemoteUserService remoteUserService;

    @Autowired
    private UserPasswordConfig userPasswordConfig;

/*    public static void main(String[] args) {
        System.out.println(BCrypt.hashpw("123456", BCrypt.gensalt()));
        System.out.println(BCrypt.checkpw("123456","$2a$10$TLTOxeIJONjOYMpimF66p.OihOr9NnPsqaaEzVZm24JAZqBBuNoHe"));
    }*/

    /**
     * 登录
     */
    public String login(LoginBody loginBody) {
        LoginUser userInfo = remoteUserService.getUserInfo(loginBody.getUsername());
        checkLogin(LoginType.PASSWORD, loginBody.getUsername(), () -> !BCrypt.checkpw(loginBody.getPassword(), userInfo.getPassword()));
        // 获取登录token
        LoginHelper.loginByDevice(userInfo, DeviceType.PC);

        return StpUtil.getTokenValue();
    }

    private void checkLogin(LoginType loginType, String username, Supplier<Boolean> supplier) {
        String errorKey = CacheConstants.PWD_ERR_CNT_KEY + username;
        String loginFail = Constants.LOGIN_FAIL;
        Integer maxRetryCount = userPasswordConfig.getMaxRetryCount();
        Integer lockTime = userPasswordConfig.getLockTime();

        // 获取用户登录错误次数(可自定义限制策略 例如: key + username + ip)
        Integer errorNumber = RedisUtils.getCacheObject(errorKey);
        // 锁定时间内登录 则踢出
        if (ObjectUtil.isNotNull(errorNumber) && errorNumber.equals(maxRetryCount)) {
            throw new BaseException(loginType.getRetryLimitExceed(), maxRetryCount, lockTime);
        }

        if (supplier.get()) {
            // 是否第一次
            errorNumber = ObjectUtil.isNull(errorNumber) ? 1 : errorNumber + 1;
            // 达到规定错误次数 则锁定登录
            if (errorNumber.equals(maxRetryCount)) {
                RedisUtils.setCacheObject(errorKey, errorNumber, Duration.ofMinutes(lockTime));
                throw new BaseException(loginType.getRetryLimitExceed(), maxRetryCount, lockTime);
            } else {
                // 未达到规定错误次数 则递增
                RedisUtils.setCacheObject(errorKey, errorNumber);
                throw new BaseException(loginType.getRetryLimitCount(), errorNumber);
            }
        }
        // 登录成功 清空错误次数
        RedisUtils.deleteObject(errorKey);
    }

    public void logout() {
        StpUtil.logout();
    }
}
