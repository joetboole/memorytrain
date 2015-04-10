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
        private ImageView mIvChange;
        private boolean mIsBackVisible=false;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main,container,false);
            mIvChange=(ImageView)rootView.findViewById(R.id.imagechange);
            mIvChange.setOnClickListener(mOnImageChangeListener);
            return rootView;
        }

        private View.OnClickListener mOnImageChangeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excuteAnimtor(mIvChange);
            }
        };

        private void excuteAnimtor(final ImageView view){
            PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofFloat("scaleX",1,0,1);
            PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofFloat("scaleY",1,0,1);
            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view,scaleXHolder,scaleYHolder);
            animator.setInterpolator(new LinearInterpolator());
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setBackgroundResource(R.drawable.image_42);
                }
            });
            animator.setDuration(2000).start();
        }

        @Override
        public void onClick(View v) {

        }
    }
}
