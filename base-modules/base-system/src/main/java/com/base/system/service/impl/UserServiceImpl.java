package com.base.system.service.impl;

import com.base.system.domain.User;
import com.base.system.mapper.UserMapper;
import com.base.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiuyue
 * @since 2023-03-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
