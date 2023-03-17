package com.base.system.service;

import com.base.system.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author jiuyue
 * @since 2023-03-07
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户ID查询角色权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectRolePermissionByUserId(Long userId);

}
