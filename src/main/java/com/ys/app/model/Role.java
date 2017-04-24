package com.ys.app.model;

/**
 * Created by byun.ys on 4/18/2017.
 */
public enum Role {

    USER(1),OPERATION(5),SUPERADMIN(9);

    private final int id;


    public  int getId(){
        return this.id;
    }

    Role(int id){
        this.id=id;

    }
}
