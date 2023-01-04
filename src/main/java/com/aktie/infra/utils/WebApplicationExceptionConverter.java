package com.aktie.infra.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.ws.rs.WebApplicationException;

import org.jboss.resteasy.client.exception.WebApplicationExceptionWrapper;

import com.aktie.domain.utils.exception.AktieException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author SRamos
 */
public class WebApplicationExceptionConverter {

    public static <T> T convertExceptionToObject(WebApplicationException exception, Class<T> targetClass) {
        return convertExceptionToObject(exception, targetClass, false);
    }

    public static <T> T convertExceptionToObject(WebApplicationException exception, Class<T> targetClass,
            boolean isCollectionTypeReturn) {
        final WebApplicationExceptionWrapper wre = (WebApplicationExceptionWrapper) exception;

        if (wre != null) {

            final WebApplicationException wae = wre.unwrap();

            if (wae != null
                    && wae.getResponse() != null) {

                final Object response = wae.getResponse().getEntity();

                if (response != null
                        && response instanceof ByteArrayInputStream) {

                    try {
                        final ByteArrayInputStream bais = (ByteArrayInputStream) response;
                        int n = bais.available();
                        byte[] bytes = new byte[n];
                        bais.read(bytes, 0, n);

                        String completeError;

                        try {
                            completeError = new String(gzipUncompress(bytes), StandardCharsets.UTF_8);
                        } catch (Exception e) {
                            completeError = new String(bytes, StandardCharsets.UTF_8);
                        }

                        if (!completeError.isEmpty()) {

                            if (targetClass.isAssignableFrom(String.class)) {
                                return (T) completeError;
                            }

                            final Gson gson = new Gson();

                            if (isCollectionTypeReturn) {
                                final Type listType = TypeToken.getParameterized(List.class, targetClass).getType();

                                final List<T> list = gson.fromJson(completeError, listType);

                                if (!list.isEmpty()) {
                                    return (T) list.get(0);
                                }
                                throw new AktieException("Erro na comunicação externa", "-999");
                            } else {
                                return gson.fromJson(completeError, targetClass);
                            }
                        }
                    } catch (Exception e) {
                        throw new AktieException("Erro na comunicação externa.", "-999");
                    }
                }
            }
        }

        return null;
    }

    private static byte[] gzipUncompress(byte[] compressedBytes) throws IOException {
        try (InputStream inputStream = new GZIPInputStream(new ByteArrayInputStream(compressedBytes))) {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                inputStream.transferTo(outputStream);
                return outputStream.toByteArray();
            }
        }
    }

}
