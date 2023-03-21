package com.base.common.mybatis.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.base.common.core.domain.BaseEntity;

import com.base.common.core.exception.base.BaseException;
import com.base.common.core.utils.StringUtils;
import com.base.common.satoken.utils.LoginHelper;
import com.base.system.api.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * MP注入处理器
 *
 */
@Slf4j
public class CreateAndUpdateMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            if (ObjectUtil.isNotNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
                Date current = ObjectUtil.isNotNull(baseEntity.getCreateTime())
                    ? baseEntity.getCreateTime() : new Date();
                baseEntity.setCreateTime(current);
                baseEntity.setUpdateTime(current);
                Long userId = ObjectUtil.isNotNull(baseEntity.getCreateBy())
                    ? baseEntity.getCreateBy() : getLoginUserId();
                // 当前已登录 且 创建人为空 则填充
                baseEntity.setCreateBy(userId);
                // 当前已登录 且 更新人为空 则填充
                baseEntity.setUpdateBy(userId);
            }
        } catch (Exception e) {
            throw new BaseException(HttpStatus.HTTP_UNAUTHORIZED,"自动注入异常 => " + e.getMessage());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            if (ObjectUtil.isNotNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
                Date current = new Date();
                // 更新时间填充(不管为不为空)
                baseEntity.setUpdateTime(current);
                Long userId = getLoginUserId();
                // 当前已登录 更新人填充(不管为不为空)
                if (ObjectUtil.isNotNull(userId)) {
                    baseEntity.setUpdateBy(userId);
                }
            }
        } catch (Exception e) {
            throw new BaseException(HttpStatus.HTTP_UNAUTHORIZED,"自动注入异常 => " + e.getMessage());
        }
    }

    /**
     * 获取登录用户名
     */
    private Long getLoginUserId() {
        LoginUser loginUser;
        try {
            loginUser = LoginHelper.getLoginUser();
        } catch (Exception e) {
            log.warn("自动注入警告 => 用户未登录");
            return null;
        }
        return loginUser.getUserId();
    }

}
