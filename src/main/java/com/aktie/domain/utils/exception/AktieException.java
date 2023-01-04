package com.aktie.domain.utils.exception;

import com.aktie.domain.entities.enums.EnumErrorCode;
import com.aktie.domain.utils.StringUtil;

/**
 *
 * @author SRamos
 */
public class AktieException extends RuntimeException {

    private String errorCode = "-1";

    public AktieException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public AktieException(EnumErrorCode error) {
        super(error.getErro());
        this.errorCode = error.getKey();
    }

    public AktieException(EnumErrorCode error, Object... args) {
        super(StringUtil.stringPatternFormat(error.getErro(), args));
        this.errorCode = error.getKey();
    }

    public String getErrorCode() {
        return errorCode;
    }

}
