package com.aktie.domain.utils;

import java.text.MessageFormat;

/**
 *
 * @author SRamos
 */
public class StringUtil {

    public static String stringPatternFormat(String pattern, Object... arguments) {
        return MessageFormat.format(pattern, arguments);
    }

    public static String toString(Object value) {
        return value != null ? value.toString() : null;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty() || str.equalsIgnoreCase("null");
    }

    public static String numbers(String str) {
        final String n = Utils.nvl(str, "");
        return n.replaceAll("[^0-9]", "");
    }

    public static String onlyNumbers(String s) {
        String sRet = "";

        if (s != null) {
            sRet = numbers(s);
        }

        return sRet;
    }

    public static String addBlankLeftPad(String campo, int tamanhoCampo) {
        if (campo == null) {
            return lpad(campo, " ", tamanhoCampo);
        }
        if (campo.length() > tamanhoCampo) {
            campo = campo.substring(0, tamanhoCampo);
        }
        return lpad(campo, " ", tamanhoCampo);
    }

    public static String lpad(String valueToPad, String filler, int size) {

        if (valueToPad == null) {
            valueToPad = "";
        }
        if (valueToPad.length() >= size) {
            valueToPad = valueToPad.substring(0, size);
        }

        while (valueToPad.length() < size) {
            valueToPad = filler + valueToPad;
        }
        return valueToPad;
    }

}
