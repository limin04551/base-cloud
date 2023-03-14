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
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

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
    public Integer deduct(String commodityCode, int count) throws BaseException {
        String currentXid = RootContext.getXID();
        log.info("全局事务id:{}", currentXid);


        //模拟重试导致幂等问题
//        if (xid == null || !xid.equals(currentXid)) {
//            xid = currentXid;
//            try {
//                log.info("模拟第一次请求dubbo超时");
//                Thread.sleep(3200);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        } else {
//            try {
//                log.info("模拟第二次请求dubbo处理时长");
//                Thread.sleep(1500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
        Storage storage = storageService.getOne(Wrappers.<Storage>lambdaQuery().eq(Storage::getCommodityCode, commodityCode));

        if (ObjectUtil.isNull(storage)) {
            throw new BaseException("商品不存在");
        }
        int leftCount = storage.getCount() - count;
        log.info("剩余:"+leftCount);
        if (leftCount < 0) {
            throw new BaseException("库存不足");
        }
//        try {
//            log.info("模拟业务处理");
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        storageService.update(Wrappers.<Storage>lambdaUpdate()
                .eq(Storage::getCommodityCode, commodityCode)
                .set(Storage::getCount, leftCount)
        );
        log.info("商品{}库存{}扣除{}剩余{}", storage.getCommodityCode(), storage, count, leftCount);
        return leftCount;
    }
}
