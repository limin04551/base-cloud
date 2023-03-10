package com.base.system.service.impl;

import com.base.system.domain.Role;
import com.base.system.mapper.RoleMapper;
import com.base.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author jiuyue
 * @since 2023-03-07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
