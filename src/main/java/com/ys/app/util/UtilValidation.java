package com.ys.app.util;

/**
 * @author chris.an
 */
public class UtilValidation {

    public static boolean isNull(Object... objects) {
        for (Object o : objects) {
            if (o == null)
                return true;
        }
        return false;
    }

    public  static  boolean isPositiveInt(Integer i){
        return i>0;
    }

    public  static  boolean isNegativeInt(Integer i){
        return i<0;
    }
}
