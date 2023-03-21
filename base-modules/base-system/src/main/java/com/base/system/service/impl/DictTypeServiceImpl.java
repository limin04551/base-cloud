package com.base.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.common.core.constant.UserConstants;
import com.base.common.core.exception.base.BaseException;
import com.base.common.core.utils.StringUtils;
import com.base.common.mybatis.core.page.PageQuery;
import com.base.system.domain.DictData;
import com.base.system.domain.DictType;
import com.base.system.mapper.DictDataMapper;
import com.base.system.mapper.DictTypeMapper;
import com.base.system.service.DictTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 字典类型表 服务实现类
 * </p>
 *
 * @author jiuyue
 * @since 2023-03-20
 */
@RequiredArgsConstructor
@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements DictTypeService {
    
    private final DictTypeMapper baseMapper;
    
    private final DictDataMapper dictDataMapper;

    @Override
    public Page<DictType> selectPageDictTypeList(DictType dictType, PageQuery pageQuery) {
        Map<String, Object> params = dictType.getParams();
        LambdaQueryWrapper<DictType> lqw = new LambdaQueryWrapper<DictType>()
                .like(StringUtils.isNotBlank(dictType.getDictName()), DictType::getDictName, dictType.getDictName())
                .eq(ObjectUtil.isNotNull(dictType.getStatus()), DictType::getStatus, dictType.getStatus())
                .like(StringUtils.isNotBlank(dictType.getDictType()), DictType::getDictType, dictType.getDictType())
                .between(params.get("beginTime") != null && params.get("endTime") != null,
                        DictType::getCreateTime, params.get("beginTime"), params.get("endTime"));
        Page<DictType> page = baseMapper.selectPage(pageQuery.build(), lqw);
        return page;
    }

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<DictType> selectDictTypeList(DictType dictType) {
        Map<String, Object> params = dictType.getParams();
        return baseMapper.selectList(new LambdaQueryWrapper<DictType>()
                .like(StringUtils.isNotBlank(dictType.getDictName()), DictType::getDictName, dictType.getDictName())
                .eq(ObjectUtil.isNotNull(dictType.getStatus()), DictType::getStatus, dictType.getStatus())
                .like(StringUtils.isNotBlank(dictType.getDictType()), DictType::getDictType, dictType.getDictType())
                .between(params.get("beginTime") != null && params.get("endTime") != null,
                        DictType::getCreateTime, params.get("beginTime"), params.get("endTime")));
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Override
    public List<DictType> selectDictTypeAll() {
        return this.list();
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<DictData> selectDictDataByType(String dictType) {
        List<DictData> dictDatas = dictDataMapper.selectDictDataByType(dictType);
        if (CollUtil.isNotEmpty(dictDatas)) {
            return dictDatas;
        }
        return null;
    }

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public DictType selectDictTypeById(Long dictId) {
        return baseMapper.selectById(dictId);
    }

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    @Override
    public DictType selectDictTypeByType(String dictType) {
        return baseMapper.selectById(new LambdaQueryWrapper<DictType>().eq(DictType::getDictType, dictType));
    }

    /**
     * 批量删除字典类型信息
     *
     * @param dictIds 需要删除的字典ID
     */
    @Override
    public void deleteDictTypeByIds(Long[] dictIds) {
        for (Long dictId : dictIds) {
            DictType dictType = selectDictTypeById(dictId);
            if (dictDataMapper.exists(new LambdaQueryWrapper<DictData>()
                    .eq(DictData::getDictType, dictType.getDictType()))) {
                throw new BaseException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
        }
        baseMapper.deleteBatchIds(Arrays.asList(dictIds));
    }

    /**
     * 新增保存字典类型信息
     *
     * @param dict 字典类型信息
     * @return 结果
     */
    @Override
    public void insertDictType(DictType dict) {
        baseMapper.insert(dict);
    }

    /**
     * 修改保存字典类型信息
     *
     * @param dict 字典类型信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDictType(DictType dict) {
        DictType oldDict = baseMapper.selectById(dict.getDictId());
        dictDataMapper.update(null, new LambdaUpdateWrapper<DictData>()
                .set(DictData::getDictType, dict.getDictType())
                .eq(DictData::getDictType, oldDict.getDictType()));
        baseMapper.updateById(dict);
    }

    /**
     * 校验字典类型称是否唯一
     *
     * @param dict 字典类型
     * @return 结果
     */
    @Override
    public int checkDictTypeUnique(DictType dict) {
        boolean exist = baseMapper.exists(new LambdaQueryWrapper<DictType>()
                .eq(DictType::getDictType, dict.getDictType())
                .ne(ObjectUtil.isNotNull(dict.getDictId()), DictType::getDictId, dict.getDictId()));
        if (exist) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
