package com.example.joez.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.joez.memorytrain.MemoryTrainApplication;
import com.example.joez.memorytrain.R;
import com.example.joez.model.CardModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class DigitalPileAdapter extends BaseAdapter{
    private List<CardModel> mCardsList;
    private LayoutInflater inflater;
    public DigitalPileAdapter(Context context){
        inflater=LayoutInflater.from(context);
        mCardsList= MemoryTrainApplication.getInstance().getCardsPiles();
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
            convertView=inflater.inflate(R.layout.small_front_item,parent,false);
            viewHolder.mTvFront=(TextView)convertView.findViewById(R.id.tv_pile_front);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        CardModel model=mCardsList.get(position);
        viewHolder.mTvFront.setText(model.getRememberNum());
        return convertView;
    }
    private static class ViewHolder{
        TextView mTvFront;
    }
}
