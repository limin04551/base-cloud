package com.base.system.service.impl;

import com.base.system.domain.RoleMenu;
import com.base.system.mapper.RoleMenuMapper;
import com.base.system.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author jiuyue
 * @since 2023-03-07
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}
