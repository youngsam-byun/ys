package com.ys.app.util;

import org.mockito.Mockito;

/**
 * Created by byun.ys on 4/17/2017.
 */
public class TestDoubles {


    public  static<T> T dummy(Class<T> t){
        return Mockito.mock(t);
    }

    public  static<T> T stub(Class<T> t){
        return Mockito.mock(t);
    }

    public  static<T> T mock(Class<T> t){
        return Mockito.mock(t);
    }

    public static <T> T spy(Class<T> t) {return  Mockito.spy(t);}
}
