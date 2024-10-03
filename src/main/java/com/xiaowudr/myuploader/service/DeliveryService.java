package com.xiaowudr.myuploader.service;

import com.xiaowudr.myuploader.mapper.DeliveryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryMapper deliveryMapper;

    public boolean deliverItem(String gameAccount, int itemId, int itemCount) {
        try {
            // 调用Mapper执行SQL插入
            deliveryMapper.insertRechargeRecord(gameAccount, itemId, itemCount);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
