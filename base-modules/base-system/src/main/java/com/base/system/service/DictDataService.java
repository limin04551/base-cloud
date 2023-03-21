package com.base.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.common.mybatis.core.page.PageQuery;
import com.base.system.domain.DictData;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 字典数据表 服务类
 * </p>
 *
 * @author jiuyue
 * @since 2023-03-20
 */
public interface DictDataService extends IService<DictData> {

    Page<DictData> selectPageDictDataList(DictData dictData, PageQuery pageQuery);

    DictData selectDictDataById(Long dictCode);

    void insertDictData(DictData dict);

    void updateDictData(DictData dict);

    void deleteDictDataByIds(Long[] dictCodes);
}
