package com.aktie.domain.utils.exception;

/**
 *
 * @author SRamos
 */
public class AktieExceptionResponseDto {

    private String errorDate;

    private String error;

    private String errorCode;

    private String httpCodeMessage;

    public String getErrorDate() {
        return this.errorDate;
    }

    public void setErrorDate(String errorDate) {
        this.errorDate = errorDate;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getHttpCodeMessage() {
        return this.httpCodeMessage;
    }

    public void setHttpCodeMessage(String httpCodeMessage) {
        this.httpCodeMessage = httpCodeMessage;
    }

}