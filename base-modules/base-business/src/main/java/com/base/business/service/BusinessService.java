package com.base.business.service;

public interface BusinessService {

    void purchase(String userId, String commodityCode, int orderCount, int money);
}
