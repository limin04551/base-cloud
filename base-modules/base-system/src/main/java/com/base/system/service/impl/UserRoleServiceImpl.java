package com.base.system.service.impl;

import com.base.system.domain.UserRole;
import com.base.system.mapper.UserRoleMapper;
import com.base.system.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author jiuyue
 * @since 2023-03-07
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
