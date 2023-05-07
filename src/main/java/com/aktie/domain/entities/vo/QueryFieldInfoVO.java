package com.aktie.domain.entities.vo;

/**
 *
 * @author SRamos
 */
public class QueryFieldInfoVO {
    
    private String fieldName;

    private Object fieldValue;

    public QueryFieldInfoVO(final String fieldName, final Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
    
}