package com.base.system.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.common.core.constant.UserConstants;
import com.base.common.core.exception.base.BaseException;
import com.base.common.mybatis.core.page.PageQuery;
import com.base.system.domain.DictType;
import com.base.system.service.DictTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 字典类型表 前端控制器
 * </p>
 *
 * @author jiuyue
 * @since 2023-03-20
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/dict/type")
public class DictTypeController {
    private final DictTypeService dictTypeService;

    /**
     * 查询字典类型列表
     */
    @SaCheckPermission("system:dict:list")
    @GetMapping("/list")
    public Page<DictType> list(DictType dictType, PageQuery pageQuery) {
        return dictTypeService.selectPageDictTypeList(dictType, pageQuery);
    }


    /**
     * 查询字典类型详细
     *
     * @param dictId 字典ID
     */
    @SaCheckPermission("system:dict:query")
    @GetMapping(value = "/{dictId}")
    public DictType getInfo(@PathVariable Long dictId) {
        return dictTypeService.selectDictTypeById(dictId);
    }

    /**
     * 新增字典类型
     */
    @SaCheckPermission("system:dict:add")
    @PostMapping
    public void add(@Validated @RequestBody DictType dict) {
        if (dictTypeService.checkDictTypeUnique(dict)==(UserConstants.NOT_UNIQUE)) {
           throw new BaseException("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dictTypeService.insertDictType(dict);
    }

    /**
     * 修改字典类型
     */
    @SaCheckPermission("system:dict:edit")
    @PutMapping
    public void edit(@Validated @RequestBody DictType dict) {
        if (dictTypeService.checkDictTypeUnique(dict)==(UserConstants.NOT_UNIQUE)) {
            throw new BaseException("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dictTypeService.updateDictType(dict);
    }

    /**
     * 删除字典类型
     *
     * @param dictIds 字典ID串
     */
    @SaCheckPermission("system:dict:remove")
    @DeleteMapping("/{dictIds}")
    public void remove(@PathVariable Long[] dictIds) {
        dictTypeService.deleteDictTypeByIds(dictIds);
    }


    /**
     * 获取字典选择框列表
     */
    @GetMapping("/optionselect")
    public List<DictType> optionselect() {
        return dictTypeService.selectDictTypeAll();
    }
}

