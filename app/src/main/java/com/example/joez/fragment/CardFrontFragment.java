package com.example.joez.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joez.memorytrain.MainActivity;
import com.example.joez.memorytrain.R;
import com.example.joez.model.CardModel;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class CardFrontFragment extends Fragment {
    private static final String KEY_PILE="pile";
    private static final int MODE_ALL=1;
    private static final int MODE_FAILURE=2;
    private String mPile;
    private TextView mTvCountTime;
    private boolean mIsAutoStartTimmer;
    private int mCurrentMode=MODE_ALL;


    public static CardFrontFragment newInstance(String pile){
        CardFrontFragment cardFrontFragment=new CardFrontFragment();
        Bundle bundle=new Bundle();
        bundle.putString(KEY_PILE,pile);
        cardFrontFragment.setArguments(bundle);
        return cardFrontFragment;
    }

    public CardFrontFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_front, container, false);
        final  TextView tvFront=(TextView)root.findViewById(R.id.tv_front);
        tvFront.setText(mPile);
        Button btnClearFailures=(Button)root.findViewById(R.id.btn_clearfailures);
        btnClearFailures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).clearFailurePiles();
                 mCurrentMode=MODE_ALL;
                mPile = ((MainActivity) getActivity()).getCurrentCardPile().getRememberNum();
                tvFront.setText(mPile);
                mTimerCountDown.cancel();
                mTimerCountDown.start();
            }
        });

        Button btnFailureLoop=(Button)root.findViewById(R.id.btn_failureloop);
        btnFailureLoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CardModel> failurePiles=((MainActivity)getActivity()).getFailurePiles();
                if(failurePiles!=null&&failurePiles.size()>0) {
                    mCurrentMode = MODE_FAILURE;
                    mPile=failurePiles.get(0).getRememberNum();
                    ((MainActivity)getActivity()).startFailureLoop();
                    mPile = ((MainActivity) getActivity()).getCurrentCardPile().getRememberNum();
                    tvFront.setText(mPile);
                    mTimerCountDown.cancel();
                    mTimerCountDown.start();
                }
            }
        });
        mTvCountTime=(TextView)root.findViewById(R.id.tv_counttime);

        Button btnNext=(Button)root.findViewById(R.id.tv_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimerCountDown.cancel();
                mTimerCountDown.start();
                if(mCurrentMode==MODE_ALL) {
                    if (((MainActivity) getActivity()).seeNextPiles()) {
                        mPile = ((MainActivity) getActivity()).getCurrentCardPile().getRememberNum();
                        tvFront.setText(mPile);
                    }
                }else if(mCurrentMode==MODE_FAILURE){
                   if( ((MainActivity)getActivity()).seeNextFailureLoop()){
                       mPile = ((MainActivity) getActivity()).getCurrentCardPile().getRememberNum();
                       tvFront.setText(mPile);
                   }
                }
            }
        });
        Button btnFail=(Button)root.findViewById(R.id.tv_fail);
        btnFail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimerCountDown.cancel();
                ((MainActivity)getActivity()).addFailurePile();
                ((MainActivity)getActivity()).flipCard();
            }
        });
        if(mIsAutoStartTimmer) {
            mIsAutoStartTimmer=false;
            mTimerCountDown.start();
        }else{
            mTvCountTime.setText("failure!");
        }
        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.e("debug","front@@@attach");
        mIsAutoStartTimmer=true;
        mPile=getArguments().getString(KEY_PILE,"");
    }

    private CountDownTimer mTimerCountDown=new CountDownTimer(6000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            mTvCountTime.setText(String.format("%s s",millisUntilFinished/1000));
        }

        @Override
        public void onFinish() {
            ((MainActivity)getActivity()).addFailurePile();
           mTvCountTime.setText("failure!");
        }
    };

    @Override
    public void onDetach() {
        mTimerCountDown.cancel();
        super.onDetach();
    }
}
