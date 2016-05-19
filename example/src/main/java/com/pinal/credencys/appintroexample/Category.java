package com.pinal.credencys.appintroexample;

/**
 * Created by pinal on 19/5/16.
 */
public class Category {

    private int id;
    private String village;

    public Category(){}

    public Category(int id, String village){
        this.id = id;
        this.village = village;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String ncity_ame){
        this.village = village;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.village;
    }

}