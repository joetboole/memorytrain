package com.example.joez.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joez.memorytrain.MainActivity;
import com.example.joez.memorytrain.R;

public class CardFrontFragment extends Fragment {
    private static final String KEY_PILE="pile";
    private String mPile;

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
        TextView tvFront=(TextView)root.findViewById(R.id.tv_front);
        tvFront.setText(mPile);
        tvFront.setOnClickListener(new View.OnClickListener() {
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
        mPile=getArguments().getString(KEY_PILE,"");
    }

}
