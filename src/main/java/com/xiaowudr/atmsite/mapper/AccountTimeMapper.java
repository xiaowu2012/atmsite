package com.xiaowudr.atmsite.mapper;

import com.xiaowudr.atmsite.pojo.AccountTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountTimeMapper {
    void insertAccountTime(AccountTime accountTime);

    // 调用存储过程方法，传入money参数
    void callAddUserAccountTime(@Param("account") String account, @Param("money") int money);
}
