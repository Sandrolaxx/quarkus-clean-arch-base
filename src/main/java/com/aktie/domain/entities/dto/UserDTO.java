package com.aktie.domain.entities.dto;

import java.util.UUID;

/**
 *
 * @author SRamos
 */
public class UserDTO {

    private UUID id;
    
    private String name;

    private String document;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

}
