package com.aktie.domain.entities.vo;

import com.aktie.domain.entities.enums.EnumErrorCode;
import com.aktie.domain.utils.StringUtil;
import com.aktie.domain.utils.exception.AktieException;

public class UserNameVO {

    public String value;

    public UserNameVO(String name) {
        validate();
        
        this.value = name;
    }

    public String getValue() {
        return this.value;
    }

    private validate() {
        if (StringUtil.isNullOrEmpty(name)) {
            throw new AktieException(EnumErrorCode.CAMPO_OBRIGATORIO, "Nome");
        }
    }
}
