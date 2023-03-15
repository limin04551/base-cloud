package com.base.common.core.enumeration;


import lombok.Getter;

/**
 * 枚举类接口
 */

public interface BaseEnum {

    /**
     * 获取枚举类的值
     *
     * @return
     */
    Object getValue();

    /**
     * 获取枚举类的说明
     *
     * @return String
     */
    String getDesc();

}
