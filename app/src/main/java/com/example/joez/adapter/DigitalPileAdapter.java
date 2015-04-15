package com.example.joez.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.joez.memorytrain.R;
import com.example.joez.model.CardModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class DigitalPileAdapter extends BaseAdapter{
    private List<CardModel> mCardsList=new ArrayList<CardModel>();
    private static final int TYPE_NUM_PILES=1;
    private LayoutInflater inflater;
    public DigitalPileAdapter(Context context){
        inflater=LayoutInflater.from(context);
        sortAndInitPilesList();
    }

    private void sortAndInitPilesList(){
          if(mCardsList.size()>0){
              mCardsList.clear();
          }
        for (int i=0;i<15;i++){
            String rememberId=String.valueOf(i);
            if(i>=100){
                rememberId=String.format("0%d",i-100);
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
            Log.e("debug","name:"+name+" and imageid:"+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int getCount() {
        return mCardsList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.fragment_front,parent,false);
            viewHolder.mTvFront=(TextView)convertView.findViewById(R.id.tv_front);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        CardModel model=mCardsList.get(position);
        viewHolder.mTvFront.setText(model.getRememberNum());
        viewHolder.mTvFront.setBackgroundResource(model.getResourceId());
        return convertView;
    }
    private static class ViewHolder{
        TextView mTvFront;
    }
}
