package com.example.joez.memorytrainapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment()).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment implements View.OnClickListener {
        private TextView mTvChange;
        private boolean mIsBackVisible=false;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main,container,false);
            mTvChange=(TextView)rootView.findViewById(R.id.imagechange);
            mTvChange.setOnClickListener(mOnTvChangeListener);
            return rootView;
        }

        private View.OnClickListener mOnTvChangeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excuteAnimtor(mTvChange);
            }
        };

        private void excuteAnimtor(final TextView view){
            PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofFloat("rotationY",0,90f);
            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view,scaleXHolder);
            animator.setInterpolator(new LinearInterpolator());
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setBackgroundResource(R.drawable.image_42);
                    view.setText("");
                    excuteAnimatorBack(view);
                }
            });

            animator.setDuration(500).start();
        }

        private void excuteAnimatorBack(final TextView view){
            ObjectAnimator animator = ObjectAnimator.ofFloat(view,"rotationY",90,180);
            animator.setInterpolator(new LinearInterpolator());
            animator.setDuration(500).start();
        }



        @Override
        public void onClick(View v) {

        }
    }
}
