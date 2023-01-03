package com.aktie.domain.entities.vo;

import com.aktie.utils.StringUtil;

public class UserNameVO {

    public String value;

    public UserNameVO(String name) {
        if (StringUtil.isNullOrEmpty(name)) {
            throw new RuntimeException("Nome não pode ser nulo ou \"\"");
        }

        this.value = name;
    }

    public String getValue() {
        return this.value;
    }
}
