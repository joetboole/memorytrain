package com.example.joez.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.joez.adapter.DigitalPileAdapter;
import com.example.joez.memorytrain.MainActivity;
import com.example.joez.memorytrain.R;

public class DigitalPileFragment extends Fragment {


    public DigitalPileFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_digital_pile, container, false);
        GridView gvPiles=(GridView)root.findViewById(R.id.gv_piles);
        DigitalPileAdapter pilesAdapter=new DigitalPileAdapter(getActivity());
        gvPiles.setAdapter(pilesAdapter);
        gvPiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity)getActivity()).seePilesDetail();
            }
        });
        return root;
    }

}
