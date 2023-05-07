package com.aktie.domain.entities.vo;

import java.time.LocalDateTime;

/**
 *
 * @author SRamos
 */
public class CreatedAtVO {
    
    final LocalDateTime createdAt;

    public CreatedAtVO(final LocalDateTime createdAt) {
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public LocalDateTime getValue() {
        return createdAt;
    }
}