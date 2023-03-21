package com.base.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.common.core.exception.base.BaseException;
import com.base.common.mybatis.core.page.PageQuery;
import com.base.system.domain.DictData;
import com.base.system.mapper.DictDataMapper;
import com.base.system.service.DictDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典数据表 服务实现类
 * </p>
 *
 * @author jiuyue
 * @since 2023-03-20
 */
@Service
public class DictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData> implements DictDataService {

    @Override
    public Page<DictData> selectPageDictDataList(DictData dictData, PageQuery pageQuery) {
        Page<DictData> page = baseMapper.selectPage(pageQuery.build(), Wrappers.<DictData>lambdaQuery()
                .eq(StrUtil.isNotBlank(dictData.getDictType()), DictData::getDictType, dictData.getDictType())
                .like(StrUtil.isNotBlank(dictData.getDictLabel()), DictData::getDictLabel, dictData.getDictLabel())
                .eq(ObjectUtil.isNotNull(dictData.getStatus()), DictData::getStatus, dictData.getStatus())
                .orderByAsc(DictData::getDictSort));
        return page;
    }

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据id
     * @return 字典数据
     */
    @Override
    public DictData selectDictDataById(Long dictCode) {
        return baseMapper.selectById(dictCode);
    }

    /**
     * 新增保存字典数据信息
     *
     * @param data 字典数据信息
     * @return 结果
     */
    @Override
    public void insertDictData(DictData data) {
        baseMapper.insert(data);
    }

    /**
     * 修改保存字典数据信息
     *
     * @param data 字典数据信息
     * @return 结果
     */
    @Override
    public void updateDictData(DictData data) {
        baseMapper.updateById(data);
    }

    @Override
    public void deleteDictDataByIds(Long[] dictCodes) {
        for (Long dictCode : dictCodes) {
            baseMapper.deleteById(dictCode);
        }
    }
}
