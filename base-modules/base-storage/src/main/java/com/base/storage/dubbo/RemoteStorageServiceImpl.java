package com.base.storage.dubbo;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.base.common.core.exception.base.BaseException;
import com.base.storage.api.RemoteStorageService;
import com.base.storage.domain.Storage;
import com.base.storage.service.StorageService;
import io.seata.core.context.RootContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RemoteStorageServiceImpl implements RemoteStorageService {

    private final StorageService storageService;

    @Override
    public void deduct(String commodityCode, int count) throws BaseException {
        log.info("全局事务id:{}" , RootContext.getXID());
        Storage storage = storageService.getOne(Wrappers.<Storage>lambdaQuery().eq(Storage::getCommodityCode, commodityCode));
        if (ObjectUtil.isNull(storage)) {
            throw new BaseException("商品不存在");
        }
        int leftCount = storage.getCount() - count;
        if (leftCount < 0) {
            throw new BaseException("库存不足");
        }
        storageService.update(Wrappers.<Storage>lambdaUpdate()
                .eq(Storage::getCommodityCode, commodityCode)
                .set(Storage::getCount, leftCount)
        );
        log.info("商品{}库存{}扣除{}剩余{}",storage.getCommodityCode(),storage,count,leftCount);
    }
}
