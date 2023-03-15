package com.base.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author limin
 * @description 登录类型
 * @date 2023/3/10
 */
@Getter
@AllArgsConstructor
public enum LoginType implements BaseEnum{

    /**
     * 密码登录
     */
    PASSWORD( "password","","user.password.retry.limit.exceed", "user.password.retry.limit.count"),

    /**
     * 短信登录
     */
    SMS("sms","","sms.code.retry.limit.exceed", "sms.code.retry.limit.count"),

    /**
     * 小程序登录
     */
    XCX("xcx","","", "");

    private final String value;

    private final String desc;
    /**
     * 登录重试超出限制提示
     */
    private final String retryLimitExceed;

    /**
     * 登录重试限制计数提示
     */
    private final String retryLimitCount;


}
