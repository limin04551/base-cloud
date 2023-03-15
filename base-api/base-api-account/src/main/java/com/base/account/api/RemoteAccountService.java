package com.base.account.api;

import com.base.account.api.model.Account;
import com.base.common.core.exception.base.BaseException;
import jakarta.validation.ValidationException;

/**
 * @author min
 * @description
 * @date 2023/3/8
 */
public interface RemoteAccountService {

    /**
     * 从用户账户中借出
     */
    void debit(Account account) throws BaseException, ValidationException;

}
