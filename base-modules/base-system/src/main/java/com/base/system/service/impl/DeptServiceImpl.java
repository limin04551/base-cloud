package com.base.system.service.impl;

import com.base.system.domain.Dept;
import com.base.system.mapper.DeptMapper;
import com.base.system.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author jiuyue
 * @since 2023-03-07
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

}
