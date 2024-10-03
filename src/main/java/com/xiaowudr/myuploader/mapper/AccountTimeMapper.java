package com.xiaowudr.myuploader.mapper;

import com.xiaowudr.myuploader.pojo.AccountTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountTimeMapper {
    void insertAccountTime(AccountTime accountTime);

    // 调用存储过程方法，传入money参数
    void callAddUserAccountTime(@Param("account") String account, @Param("money") int money);
}
