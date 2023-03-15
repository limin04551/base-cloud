package com.base.account.service.impl;

import com.base.account.api.model.Account;
import com.base.account.mapper.AccountMapper;
import com.base.account.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiuyue
 * @since 2023-03-09
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

}
