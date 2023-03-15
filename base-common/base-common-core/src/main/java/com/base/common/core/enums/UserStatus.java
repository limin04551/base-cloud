package com.base.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态
 *
 * @author ruoyi
 */
@Getter
@AllArgsConstructor
public enum UserStatus {
    OK(1, "正常"), DISABLE(0, "停用"), DELETED(2, "删除");

    private final Integer code;
    private final String info;

}
