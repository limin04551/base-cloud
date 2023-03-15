package com.base.account.dubbo;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.base.account.api.RemoteAccountService;
import com.base.account.api.model.Account;
import com.base.account.service.AccountService;
import com.base.common.core.exception.base.BaseException;
import io.seata.core.context.RootContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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
    public void debit(@Validated Account accountInfo) throws BaseException {
        log.info("全局事务id:{}" , RootContext.getXID());
//        if(xid == null||!xid.equals(RootContext.getXID())){
//            xid = RootContext.getXID();
//            try {
//                log.info("模拟第一次请求dubbo超时");
//                Thread.sleep(3500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
        Account account = accountService.getOne(Wrappers.<Account>lambdaQuery().eq(Account::getUserId, accountInfo.getUserId()));
        if (ObjectUtil.isNull(account)) {
            throw new BaseException("用户不存在");
        }
        int leftMoney = account.getMoney() - accountInfo.getMoney();
        log.info("账户:{}余额{},消费:{},剩余:{}" , account.getUserId(),account.getMoney(),accountInfo.getMoney(),leftMoney);
        if (leftMoney < 0) {
            throw new BaseException("账户余额不足");
        }
//        log.info("账户:{}余额{},消费:{},剩余:{}" , account.getUserId(),account.getMoney(),money,leftMoney);
        accountService.update(Wrappers.<Account>lambdaUpdate().eq(Account::getUserId, accountInfo.getUserId())
                .set(Account::getMoney, leftMoney));
    }
}
