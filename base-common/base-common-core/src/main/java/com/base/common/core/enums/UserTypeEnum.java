package com.base.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户类型
 * @author limin
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum implements BaseEnum {

    /**
     * pc端
     */
    SYS_USER(1, "sys_user"),

    /**
     * app端
     */
    APP_USER(2, "app_user");

    private final Integer value;
    private final String desc;
}


