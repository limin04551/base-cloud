package com.base.system.dubbo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.base.common.core.enums.UserStatus;
import com.base.common.core.exception.base.BaseException;
import com.base.system.api.RemoteUserService;
import com.base.system.api.model.LoginUser;
import com.base.system.domain.User;
import com.base.system.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;


/**
 * @author limin
 * @description 操作日志记录
 * @date 2023/3/10
 */
@RequiredArgsConstructor
@Service
@DubboService
@Slf4j
public class RemoteUserServiceImpl implements RemoteUserService {

    private final UserMapper userMapper;


    @Override
    public LoginUser getUserInfo(String username) throws BaseException {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserName, username));

        if (ObjectUtil.isNull(user)) {
//            throw new RuntimeException("用户不存在");
            throw new BaseException("用户不存在");

        }
        if (user.getStatus().equals(UserStatus.DISABLE.getCode())) {
            throw new BaseException("用户被禁用");
        }
        // 此处可根据登录用户的数据不同 自行创建 loginUser
        return buildLoginUser(user);
    }

    /**
     * 构建登录用户
     */
    private LoginUser buildLoginUser(User user) {
        LoginUser loginUser = new LoginUser();
        BeanUtil.copyProperties(user, loginUser);
        return loginUser;
    }


}
