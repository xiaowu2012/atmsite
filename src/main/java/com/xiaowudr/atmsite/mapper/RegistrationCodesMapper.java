package com.xiaowudr.atmsite.mapper;

import com.xiaowudr.atmsite.pojo.RegistrationCodes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegistrationCodesMapper {
    RegistrationCodes getRegistrationCodes(String registerNo);
    void deleteRegistrationCodes(String registerNo);
    void updateIsUsed(RegistrationCodes registrationCodes);
    void insertRegistrationCodes(RegistrationCodes registrationCodes);
}
