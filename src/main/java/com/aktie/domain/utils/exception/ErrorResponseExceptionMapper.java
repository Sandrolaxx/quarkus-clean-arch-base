package com.aktie.domain.utils.exception;

import java.time.LocalDateTime;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.aktie.domain.entities.enums.EnumErrorCode;
import com.aktie.domain.utils.DateUtil;

/**
 *
 * @author SRamos
 */
@Provider
public class ErrorResponseExceptionMapper implements ExceptionMapper<AktieException> {

    @Override
    public Response toResponse(AktieException ex) {

        int BAD_REQUEST = 400;
        int httpStatus;
        var error = EnumErrorCode.parseByKey(ex.getErrorCode());

        var formattedDate = DateUtil.formatDDMMYYYYHHMMSS(LocalDateTime.now());
        var exceptionResponse = new AktieExceptionResponseDto();

        exceptionResponse.setError(ex.getMessage());
        exceptionResponse.setErrorDate(formattedDate);

        if (error != null) {
            var errorPhrase = Status.fromStatusCode(error.getHttpStatus()).getReasonPhrase();
            httpStatus = error.getHttpStatus();

            exceptionResponse.setErrorCode(error.getKey());
            exceptionResponse.setHttpCodeMessage(errorPhrase);
        } else {
            exceptionResponse.setErrorCode(ex.getErrorCode());
            httpStatus = BAD_REQUEST;
        }

        return Response.status(httpStatus).entity(exceptionResponse).build();
    }

}
