package com.ys.app.util;

/**
 * @author chris.an
 */
public class UtilValidation {

    public static boolean IsNull(Object... objects) {
        for (Object o : objects) {
            if (o == null)
                return true;
        }
        return false;
    }
}
