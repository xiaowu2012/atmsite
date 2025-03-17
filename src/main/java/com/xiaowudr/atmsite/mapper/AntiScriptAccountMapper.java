package com.xiaowudr.atmsite.mapper;

import com.xiaowudr.atmsite.pojo.AntiScriptAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AntiScriptAccountMapper {
    List<AntiScriptAccount> getAntiScriptAccount();
}
