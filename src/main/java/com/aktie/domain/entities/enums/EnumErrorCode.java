package com.aktie.domain.entities.enums;

import org.apache.http.HttpStatus;

import com.aktie.domain.utils.EnumUtil;

/**
 *
 * @author SRamos
 */
public enum EnumErrorCode implements IEnum {

    // Erros API
    CAMPO_OBRIGATORIO("001", "O campo {0} é obrigatório!", HttpStatus.SC_BAD_REQUEST),
    // Erros externos
    ERRO_COMUNICACAO("050", "A requisição enviada ao parceiro retornou com erro!", HttpStatus.SC_BAD_GATEWAY);

    private final String key, erro;
    private final int httpStatus;

    private EnumErrorCode(String key, String error, int httpStatus) {
        this.key = key;
        this.erro = error;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getKey() {
        return key;
    }

    public String getErro() {
        return erro;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    @Override
    public boolean containsInEnum(String key) {
        return parseByKey(key) != null;
    }

    public static EnumErrorCode parseByKey(String key) {
        return (EnumErrorCode) EnumUtil.parseByKey(EnumErrorCode.class, key);
    }

}
