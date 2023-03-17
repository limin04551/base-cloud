package com.base.system.mapper;

import com.base.system.domain.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author jiuyue
 * @since 2023-03-17
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectMenuPermsByUserId(Long userId);

    List<String> selectMenuPermsByRoleId(Long roleId);
}
