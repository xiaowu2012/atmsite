package com.xiaowudr.atmsite.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeliveryMapper {

    @Insert("INSERT INTO recharge_card(straccount, nitemID, nitemCount) VALUES (#{gameAccount}, #{itemId}, #{itemCount})")
    void insertRechargeRecord(String gameAccount, int itemId, int itemCount);
}