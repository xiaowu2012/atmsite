package com.xiaowudr.atmsite.service;


import com.xiaowudr.atmsite.mapper.GameUserMapper;
import com.xiaowudr.atmsite.pojo.GameUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameUserService {
    @Autowired
    private GameUserMapper gameUserMapper;

    /**
     *
     * @return List<GameUser>
     */
    public List<GameUser> getTopGameUser() {
        return gameUserMapper.listGameUsers();
    }
}
