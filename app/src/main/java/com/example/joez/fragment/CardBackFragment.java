package com.example.joez.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.joez.memorytrain.MainActivity;
import com.example.joez.memorytrain.R;
public class CardBackFragment extends Fragment {
    private static final String KEY_RESOURCEID="resourceId";
    private int mResourceId;
    public static CardBackFragment newInstance(int resourceId){
        CardBackFragment cardBackFragment=new CardBackFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(KEY_RESOURCEID,resourceId);
        cardBackFragment.setArguments(bundle);
        return cardBackFragment;
    }

    public CardBackFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_back, container, false);
        ImageView ivBack=(ImageView)root.findViewById(R.id.iv_back);
        ivBack.setBackgroundResource(mResourceId);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).flipCard();
            }
        });
        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mResourceId=getArguments().getInt(KEY_RESOURCEID);
    }
}
