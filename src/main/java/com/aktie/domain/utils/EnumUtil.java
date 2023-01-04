package com.aktie.domain.utils;

import com.aktie.domain.entities.enums.IEnum;

/**
 *
 * @author SRamos
 */
public class EnumUtil {

    public static boolean isEquals(Enum one, Enum another) {
        if (one == null && another == null) {
            return true;
        } else if (one != null && another != null) {
            return one.name().equals(another.name());
        } else {
            return false;
        }
    }

    public static boolean contains(Enum one, Enum... others) {
        if (others != null) {
            for (Enum en : others) {
                if (isEquals(one, en)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static <T extends IEnum> T parseByKey(Class<T> enumValue, String key) {
        try {
            if (key != null && !key.trim().isEmpty()) {
                for (var value : enumValue.getEnumConstants()) {
                    if (value.getKey().equalsIgnoreCase(key)) {
                        return value;
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }

}
