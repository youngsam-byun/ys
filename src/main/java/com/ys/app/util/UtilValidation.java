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

    public  static  boolean isPositiveInt(Integer... is){

        for(Integer i:is) {
             if(i>0)
                 return true;
        }
        return false;
    }

    public  static  boolean isNegativeInt(Integer... is){
        for(Integer i:is) {
            if(i<0)
                return true;
        }
        return false;

    }
}
