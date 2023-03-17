package com.base.system.service.impl;

import com.base.common.satoken.utils.LoginHelper;
import com.base.system.domain.Role;
import com.base.system.domain.User;
import com.base.system.service.MenuService;
import com.base.system.service.PermissionService;
import com.base.system.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户权限处理
 *
 * @author ruoyi
 */
@RequiredArgsConstructor
@Service
public class PermissionServiceImpl implements PermissionService {

    private final RoleService roleService;
    private final MenuService menuService;

    /**
     * 获取角色数据权限
     *
     * @param user 用户
     * @return 角色权限信息
     */
    @Override
    public Set<String> getRolePermission(User user) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (LoginHelper.isAdmin(user.getUserId())) {
            roles.add("admin");
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getUserId()));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param user 用户
     * @return 菜单权限信息
     */
    @Override
    public Set<String> getMenuPermission(User user) {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (LoginHelper.isAdmin(user.getUserId())) {
            perms.add("*:*:*");
        } else {
            List<Role> roles = user.getRoles();
            if (!roles.isEmpty() && roles.size() > 1) {
                // 多角色设置permissions属性，以便数据权限匹配权限
                for (Role role : roles) {
                    Set<String> rolePerms = menuService.selectMenuPermsByRoleId(role.getRoleId());
                    role.setPermissions(rolePerms);
                    perms.addAll(rolePerms);
                }
            } else {
                perms.addAll(menuService.selectMenuPermsByUserId(user.getUserId()));
            }
        }
        return perms;
    }
}
