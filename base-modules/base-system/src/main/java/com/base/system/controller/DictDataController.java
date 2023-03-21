package com.base.system.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.common.mybatis.core.page.PageQuery;
import com.base.system.domain.DictData;
import com.base.system.service.DictDataService;
import com.base.system.service.DictTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 字典数据表 前端控制器
 * </p>
 *
 * @author jiuyue
 * @since 2023-03-20
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/dict/data")
public class DictDataController {

    private final DictDataService dictDataService;
    private final DictTypeService dictTypeService;

    /**
     * 查询字典数据列表
     */
    @SaCheckPermission("system:dict:list")
    @GetMapping("/list")
    public Page<DictData> list(DictData dictData, PageQuery pageQuery) {
        return dictDataService.selectPageDictDataList(dictData, pageQuery);
    }


    /**
     * 查询字典数据详细
     *
     * @param dictCode 字典code
     */
    @SaCheckPermission("system:dict:query")
    @GetMapping(value = "/{dictCode}")
    public DictData getInfo(@PathVariable Long dictCode) {
        return dictDataService.selectDictDataById(dictCode);
    }

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 字典类型
     */
    @GetMapping(value = "/type/{dictType}")
    public List<DictData> dictType(@PathVariable String dictType) {
        List<DictData> data = dictTypeService.selectDictDataByType(dictType);
        if (ObjectUtil.isNull(data)) {
            data = new ArrayList<>();
        }
        return data;
    }

    /**
     * 新增字典类型
     */
    @SaCheckPermission("system:dict:add")
    @PostMapping
    public void add(@Validated @RequestBody DictData dict) {
        dictDataService.insertDictData(dict);
    }

    /**
     * 修改保存字典类型
     */
    @SaCheckPermission("system:dict:edit")
    @PutMapping
    public void edit(@Validated @RequestBody DictData dict) {
        dictDataService.updateDictData(dict);
    }

    /**
     * 删除字典类型
     *
     * @param dictCodes 字典Code串
     */
    @SaCheckPermission("system:dict:remove")
    @DeleteMapping("/{dictCodes}")
    public void remove(@PathVariable Long[] dictCodes) {
        dictDataService.deleteDictDataByIds(dictCodes);
    }

}

