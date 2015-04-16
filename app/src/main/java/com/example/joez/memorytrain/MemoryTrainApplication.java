package com.example.joez.memorytrain;

import android.app.Application;
import android.util.Log;

import com.example.joez.model.CardModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoeZ on 2015/4/15.
 */
public class MemoryTrainApplication extends Application{
    private static final int TYPE_NUM_PILES=1;
    private List<CardModel> mCardsList;
    private static MemoryTrainApplication sApplication;

    public static MemoryTrainApplication getInstance(){
        return sApplication;
    }

    @Override
    public void onCreate() {
        if(sApplication==null){
            sApplication=this;
        }
        initCardPiles();
    }

    public List<CardModel> getCardsPiles(){
        return mCardsList;
    }

    private void initCardPiles(){
        if(mCardsList==null){
            mCardsList=new ArrayList<>();
        }
        for (int i=1;i<=110;i++){
            String rememberId=String.valueOf(i);
            if(i>100){
                rememberId=String.format("0%d",i-101);
            }
            CardModel model=new CardModel(TYPE_NUM_PILES,i,rememberId,getResourceIdByName(String.format("image_%s", rememberId)));
            mCardsList.add(model);
        }
    }

    private int getResourceIdByName(String name){
        int result=0;
        try {
            Field imageFeild=R.drawable.class.getField(name);
            result=imageFeild.getInt(null);
            Log.e("debug", "name:" + name + " and imageid:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
