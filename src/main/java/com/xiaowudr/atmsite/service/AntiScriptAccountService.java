package com.xiaowudr.atmsite.service;


import com.xiaowudr.atmsite.mapper.AntiScriptAccountMapper;
import com.xiaowudr.atmsite.pojo.AntiScriptAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AntiScriptAccountService {
    @Autowired
    private AntiScriptAccountMapper antiScriptAccountMapper;

    /**
     *
     * @return List<GameUser>
     */
    public List<AntiScriptAccount> getAntiScriptAccountList() {
        return antiScriptAccountMapper.getAntiScriptAccount();
    }
}
