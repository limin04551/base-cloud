package com.base.system.domain;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色和部门关联表
 * </p>
 *
 * @author jiuyue
 * @since 2023-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role_dept")
public class RoleDept implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long roleDeptId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 逻辑删除（0:代表存在 id:代表删除）
     */
    @TableLogic
    private Long delFlag;

}
