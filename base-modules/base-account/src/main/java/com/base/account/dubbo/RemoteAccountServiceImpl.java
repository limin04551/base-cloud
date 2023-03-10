package com.base.account.dubbo;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.base.account.api.RemoteAccountService;
import com.base.account.domain.Account;
import com.base.account.mapper.AccountMapper;
import com.base.account.service.AccountService;
import com.base.common.core.exception.base.BaseException;
import io.seata.core.context.RootContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author min
 * @description
 * @date 2023/3/9
 */
@RequiredArgsConstructor
@Service
@DubboService
@Slf4j
public class RemoteAccountServiceImpl implements RemoteAccountService {

    private final AccountService accountService;


    private static String xid= null;

    @Override
    public void debit(String userId, int money) throws BaseException {
        log.info("全局事务id:{}" , RootContext.getXID());
        if(xid == null){
            xid = RootContext.getXID();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Account account = accountService.getOne(Wrappers.<Account>lambdaQuery().eq(Account::getUserId, userId));
        if (ObjectUtil.isNull(account)) {
            throw new BaseException("用户不存在");
        }
        int leftMoney = account.getMoney() - money;
        if (leftMoney < 0) {
            throw new BaseException("账户余额不足");
        }

        accountService.update(Wrappers.<Account>lambdaUpdate().eq(Account::getUserId, userId)
                .set(Account::getMoney, leftMoney));
    }
}
