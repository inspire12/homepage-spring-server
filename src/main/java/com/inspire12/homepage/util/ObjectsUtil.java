package com.inspire12.homepage.util;

import java.util.Collection;

public class ObjectsUtil {
    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isEmpty(final String target) {
        return target == null || target.length() == 0;
    }


    public static <T> boolean isEmpty(final Collection<T> target) {
        return target == null || target.isEmpty();
    }

}
