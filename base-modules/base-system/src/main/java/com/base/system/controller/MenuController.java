package com.base.system.controller;


import com.base.common.core.domain.R;
import com.base.common.satoken.utils.LoginHelper;
import com.base.system.domain.Menu;
import com.base.system.domain.vo.RouterVo;
import com.base.system.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author jiuyue
 * @since 2023-03-17
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public List<RouterVo> getRouters() {
        Long userId = LoginHelper.getUserId();
        List<Menu> menus = menuService.selectMenuTreeByUserId(userId);
        return menuService.buildMenus(menus);
    }

}

