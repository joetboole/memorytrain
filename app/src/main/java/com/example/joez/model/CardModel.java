package com.example.joez.model;

/**
 * Created by JoeZ on 2015/4/15.
 */
public class CardModel {
    private int type;
    private int number;
    private String rememberNum;
    private int resourceId;
    public  CardModel(){

    }

    public CardModel(int type,int number,String rememberNum,int resourceId){
        this.type=type;
        this.number=number;
        this.rememberNum=rememberNum;
        this.resourceId=resourceId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getRememberNum() {
        return rememberNum;
    }

    public void setRememberNum(String rememberNum) {
        this.rememberNum = rememberNum;
    }
}
