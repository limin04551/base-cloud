package com.base.common.core.constant;

/**
 * 用户常量信息
 * @author limin
 */
public interface UserConstants {
    /**
     * 平台内系统用户的唯一标志
     */
    String SYS_USER = "SYS_USER";

    /**
     * 正常状态
     */
    String NORMAL = "0";

    /**
     * 异常状态
     */
    String EXCEPTION = "1";

    /**
     * 用户正常状态
     */
    String USER_NORMAL = "0";

    /**
     * 用户封禁状态
     */
    String USER_DISABLE = "1";

    /**
     * 角色正常状态
     */
    String ROLE_NORMAL = "0";

    /**
     * 角色封禁状态
     */
    String ROLE_DISABLE = "1";

    /**
     * 部门正常状态
     */
    String DEPT_NORMAL = "0";

    /**
     * 部门停用状态
     */
    int DEPT_DISABLE = 0;

    /**
     * 字典正常状态
     */
    int DICT_NORMAL = 1;

    /**
     * 是否为系统默认（是）
     */
    String YES = "Y";

    /**
     * 是否菜单外链（是）
     */
    int YES_FRAME = 1;

    /**
     * 是否菜单外链（否）
     */
    int NO_FRAME = 0;

    /**
     * 菜单正常状态
     */
    int MENU_NORMAL = 1;

    /**
     * 菜单停用状态
     */
    int MENU_DISABLE = 0;

    /**
     * 菜单类型（目录）
     */
    String TYPE_DIR = "C";

    /**
     * 菜单类型（菜单）
     */
    String TYPE_MENU = "M";

    /**
     * 菜单类型（按钮）
     */
    String TYPE_BUTTON = "B";

    /**
     * Layout组件标识
     */
    String LAYOUT = "Layout";

    /**
     * ParentView组件标识
     */
    String PARENT_VIEW = "ParentView";

    /**
     * InnerLink组件标识
     */
    String INNER_LINK = "InnerLink";

    /**
     * 校验返回结果码
     */
    int UNIQUE = 1;

    int NOT_UNIQUE = 0;

    /**
     * 用户名长度限制
     */
    int USERNAME_MIN_LENGTH = 2;

    int USERNAME_MAX_LENGTH = 20;

    /**
     * 密码长度限制
     */
    int PASSWORD_MIN_LENGTH = 5;

    int PASSWORD_MAX_LENGTH = 20;

    /**
     * 管理员ID
     */
    Long ADMIN_ID = 1L;
}
