package com.base.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.base.common.core.constant.UserConstants;
import com.base.system.domain.DictData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 字典数据表 Mapper 接口
 * </p>
 *
 * @author jiuyue
 * @since 2023-03-20
 */
public interface DictDataMapper extends BaseMapper<DictData> {

    default List<DictData> selectDictDataByType(String dictType) {
        return selectList(
                new LambdaQueryWrapper<DictData>()
                        .eq(DictData::getStatus, UserConstants.DICT_NORMAL)
                        .eq(DictData::getDictType, dictType)
                        .orderByAsc(DictData::getDictSort));
    }
}
