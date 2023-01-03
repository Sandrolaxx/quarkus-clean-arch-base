package com.aktie.domain.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.aktie.domain.entities.vo.UserNameVO;

/**
 *
 * @author SRamos
 */
public class UserBO {

    private UUID id;

    private UserNameVO name;

    private String document;

    private LocalDateTime createdAt;

    private LocalDateTime disabledAt;

    public UserBO(UUID id, UserNameVO name, String document,
            LocalDateTime createdAt, LocalDateTime disabledAt) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.disabledAt = disabledAt;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public UserNameVO getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getDisabledAt() {
        return disabledAt;
    }

    public void handleDisable() {
        this.disabledAt = LocalDateTime.now();
    }

}
