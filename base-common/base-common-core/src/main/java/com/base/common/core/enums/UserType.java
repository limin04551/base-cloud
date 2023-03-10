package com.base.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author limin
 * @description 设备类型 针对多套 用户体系
 * @date 2023/3/10
 */
@Getter
@AllArgsConstructor
public enum UserType {

    /**
     * pc端
     */
    SYS_USER("sys_user"),

    /**
     * app端
     */
    APP_USER("app_user");

    private final String userType;

    public static UserType getUserType(String str) {
        for (UserType value : values()) {
            if (str.contains(value.getUserType())) {
                return value;
            }
        }
        throw new RuntimeException("'UserType' not found By " + str);
    }
}
