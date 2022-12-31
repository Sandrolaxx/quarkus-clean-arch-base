package com.aktie.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

/**
 *
 * @author SRamos
 */
public class NumericUtil {

    public static BigDecimal toBigDecimal(Object val, Integer decimais) {
        if (val == null) {
            return null;
        }
        var symbols1 = new DecimalFormatSymbols(new Locale("en", "US"));
        var symbols2 = new DecimalFormatSymbols(new Locale("pt", "BR"));
        var format1 = new DecimalFormat();

        format1.setDecimalFormatSymbols(symbols1);
        format1.setParseBigDecimal(true);

        String numero = null;
        if (val instanceof String) {
            try {
                int indexPonto = ((String) val).indexOf(".");
                int indexVirgula = ((String) val).indexOf(",");
                if (indexPonto != -1 && indexPonto < indexVirgula) {
                    format1.setDecimalFormatSymbols(symbols2);
                } else if (indexPonto == -1 && indexVirgula != -1) {
                    format1.setDecimalFormatSymbols(symbols2);
                }
                format1.setParseBigDecimal(true);
                val = format1.parse((String) val);
            } catch (ParseException e) {
                e.printStackTrace();
                format1.setDecimalFormatSymbols(symbols2);
                try {
                    val = format1.parse((String) val);
                } catch (ParseException e1) {
                }
            }
        }
        if (val instanceof BigDecimal) {
            return (BigDecimal) val;
        }
        format1.setDecimalFormatSymbols(symbols1);
        format1.setGroupingUsed(false);
        format1.setMaximumFractionDigits(decimais);
        if (val instanceof Number) {
            numero = format1.format(val);
        }
        return new BigDecimal(numero);
    }

    public static BigDecimal toBigDecimal(Object val) {
        return toBigDecimal(val, 2);
    }

    public synchronized static boolean isGreaterThanZero(Number number) {
        return isGreater(number, 0);
    }

    public synchronized static boolean isGreater(Number number, Number compareTo) {
        return number != null && compareTo != null && number.doubleValue() > compareTo.doubleValue();
    }

    public synchronized static boolean isLessOrEquals(Number number, Number compareTo) {
        return number != null && number.doubleValue() <= compareTo.doubleValue();
    }

    public synchronized static boolean isLessOrEqualsZero(Number number) {
        return isLessOrEquals(number, 0);
    }

    public static Number nvl(Number number, Number replacement) {
        if (number == null) {
            return replacement;
        }
        return number;
    }

    public static Number parseInt(final String parse) {
        try {
            return Integer.parseInt(parse);
        } catch (Exception e) {
        }
        return 0;
    }

    public static boolean isNullOrZero(final Number value) {
        return value == null || value.equals(0.0);
    }

}
