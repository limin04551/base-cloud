package com.base.business.controller;

import com.baomidou.lock.exception.LockFailureException;
import com.base.business.service.BusinessService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 * @description
 * @date 2023/3/8
 */
@RestController
@AllArgsConstructor
@RequestMapping("business")
public class BusinessController {

    private final BusinessService businessService;

    @GetMapping("purchase")
    public String purchase(String userId, String commodityCode, int orderCount, int money){
        try {
            return businessService.purchase(userId, commodityCode,orderCount, money);
        } catch (LockFailureException e) {
            return "大量处理中,请稍后再试...";
        }
    }



}
