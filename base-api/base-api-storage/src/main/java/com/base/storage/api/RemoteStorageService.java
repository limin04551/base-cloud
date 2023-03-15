package com.base.storage.api;

import com.base.common.core.exception.base.BaseException;
import jakarta.validation.ValidationException;

/**
 * @author min
 * @description
 * @date 2023/3/8
 */
public interface RemoteStorageService {

    /**
     * 扣除存储数量
     */
    Integer deduct(String commodityCode, int count) throws BaseException, ValidationException;

}
