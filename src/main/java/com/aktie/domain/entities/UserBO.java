package com.aktie.domain.entities;

import java.time.LocalDateTime;

import com.aktie.domain.entities.enums.EnumErrorCode;
import com.aktie.domain.entities.vo.CreatedAtVO;
import com.aktie.domain.entities.vo.UuidVO;
import com.aktie.domain.utils.StringUtil;
import com.aktie.domain.utils.exception.AktieException;

/**
 *
 * @author SRamos
 */
public class UserBO {

    private UuidVO id;

    private String name;

    private String document;

    private CreatedAtVO createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime disabledAt;

    public UserBO(UuidVO id, String name, String document, CreatedAtVO createdAt,
            LocalDateTime updatedAt, LocalDateTime disabledAt) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.disabledAt = disabledAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        validate();
    }

    public UuidVO getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public CreatedAtVO getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getDisabledAt() {
        return disabledAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void handleDisable() {
        this.disabledAt = LocalDateTime.now();
    }

    public void handleUpdateInfo(String name, String document) {
        if (StringUtil.isNotNullOrEmpty(name) && !name.equalsIgnoreCase(this.name)) {
            this.name = name;
        }

        if (StringUtil.isNotNullOrEmpty(document) && !document.equalsIgnoreCase(this.document)) {
            this.document = document;
        }

        this.updatedAt = LocalDateTime.now();
    }

    private void validate() {
        if (StringUtil.isNullOrEmpty(name)) {
            throw new AktieException(EnumErrorCode.CAMPO_OBRIGATORIO, "name");
        }

        if (StringUtil.isNullOrEmpty(document)) {
            throw new AktieException(EnumErrorCode.CAMPO_OBRIGATORIO, "document");
        }
    }

}
