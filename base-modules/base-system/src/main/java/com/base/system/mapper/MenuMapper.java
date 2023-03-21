package com.base.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.base.common.core.constant.UserConstants;
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

    default List<Menu> selectMenuTreeAll() {
        LambdaQueryWrapper<Menu> lqw = new LambdaQueryWrapper<Menu>()
                .in(Menu::getMenuType, UserConstants.TYPE_DIR, UserConstants.TYPE_MENU)
                .eq(Menu::getStatus, UserConstants.MENU_NORMAL)
                .orderByAsc(Menu::getParentId)
                .orderByAsc(Menu::getSort);
        return this.selectList(lqw);
    }

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<Menu> selectMenuTreeByUserId(Long userId);


}
