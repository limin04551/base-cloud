package com.base.system.service;

import com.base.system.domain.User;

import java.util.Set;

/**
 * 权限信息 服务层
 * @author limin
 */
public interface PermissionService {
    /**
     * 获取角色数据权限
     *
     * @param user 用户
     * @return 角色权限信息
     */
    Set<String> getRolePermission(User user);

    /**
     * 获取菜单数据权限
     *
     * @param user 用户
     * @return 菜单权限信息
     */
    Set<String> getMenuPermission(User user);
}
