package com.xiaowudr.myuploader.mapper;

import com.xiaowudr.myuploader.pojo.RegistrationCodes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegistrationCodesMapper {
    RegistrationCodes getRegistrationCodes(String registerNo);
    void deleteRegistrationCodes(String registerNo);
    void updateIsUsed(RegistrationCodes registrationCodes);
    void insertRegistrationCodes(RegistrationCodes registrationCodes);
}
