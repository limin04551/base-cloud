package com.base.account.api;

import com.base.common.core.exception.base.BaseException;

/**
 * @author min
 * @description
 * @date 2023/3/8
 */
public interface RemoteAccountService {

    /**
     * 从用户账户中借出
     */
    void debit(String userId, int money) throws BaseException;

}
