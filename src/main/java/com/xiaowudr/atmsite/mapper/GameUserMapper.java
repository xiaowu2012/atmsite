package com.xiaowudr.atmsite.mapper;

import com.xiaowudr.atmsite.pojo.GameUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GameUserMapper {
    List<GameUser> listGameUsers();
}
