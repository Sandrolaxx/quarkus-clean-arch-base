package com.aktie.presentation.dto;

/**
 *
 * @author SRamos
 */
public class UserDTO {

    private Integer id;
    
    private String name;

    private String document;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
