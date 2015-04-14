package com.example.joez.memorytrainapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new CardFrontFragment()).commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class CardFrontFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View root=(View)inflater.inflate(R.layout.fragment_front, container, false);
            ImageView ivFront=(ImageView)root.findViewById(R.id.iv_front);
            ivFront.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).flipCard();
                }
            });
            return root;
        }
    }

    public static class CardBackFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View root =  inflater.inflate(R.layout.fragment_back, container, false);
            ImageView ivBack=(ImageView)root.findViewById(R.id.iv_back);
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).flipCard();
                }
            });
            return root;
        }
    }
    private boolean mShowingBack=false;
    public void flipCard() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            mShowingBack=false;
            return;
        }
        mShowingBack = true;
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.flip_right_in, R.animator.flip_right_out,
                        R.animator.flip_left_in, R.animator.flip_left_out)
                .replace(R.id.container, new CardBackFragment())
                .addToBackStack(null)
                .commit();
    }

}
