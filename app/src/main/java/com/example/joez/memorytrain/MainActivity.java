package com.example.joez.memorytrain;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.joez.fragment.CardBackFragment;
import com.example.joez.fragment.CardFrontFragment;
import com.example.joez.fragment.DigitalPileFragment;
import com.example.joez.model.CardModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    private CardModel mCurrentCardPile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new DigitalPileFragment()).commit();
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
                .replace(R.id.container, CardBackFragment.newInstance(mCurrentCardPile.getResourceId()))
                .addToBackStack(null)
                .commit();
    }

    public void seePilesDetail(int index){
        mShowingBack=false;
        mCurrentCardPile=MemoryTrainApplication.getInstance().getCardsPiles().get(index);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, CardFrontFragment.newInstance(mCurrentCardPile.getRememberNum()))
                .addToBackStack(null)
                .commit();
    }

    public boolean seeNextPiles(){
        boolean result=false;
        mShowingBack=false;
        List<CardModel> cardPiles=MemoryTrainApplication.getInstance().getCardsPiles();
        if(mCurrentCardPile.getNumber()<cardPiles.size()) {
            mCurrentCardPile = MemoryTrainApplication.getInstance().getCardsPiles().get(mCurrentCardPile.getNumber());
            result=true;
        }
        return result;
    }

    private List<CardModel> mFailurePiles=new ArrayList<>();

    public List<CardModel> getFailurePiles(){
        return mFailurePiles;
    }

    public void startFailureLoop(){
        mCurrentCardPile=mFailurePiles.get(0);
    }

    public boolean seeNextFailureLoop(){
       boolean result=false;
       int index = mFailurePiles.indexOf(mCurrentCardPile);
        if(index+1<mFailurePiles.size()-1){
            mCurrentCardPile=mFailurePiles.get(index+1);
            result=true;
        }
        return result;
    }

    public void addFailurePile(){
        if(!mFailurePiles.contains(mCurrentCardPile)) {
            mFailurePiles.add(mCurrentCardPile);
        }
    }

    public void removeFailurePile(){
        if(mFailurePiles.contains(mCurrentCardPile)){
            mFailurePiles.remove(mCurrentCardPile);
        }
    }

    public CardModel getCurrentCardPile(){
        return mCurrentCardPile;
    }

    public void clearFailurePiles(){
        mCurrentCardPile=MemoryTrainApplication.getInstance().getCardsPiles().get(0);
        mFailurePiles.clear();
    }

    @Override
    public void onBackPressed() {
        int count=getFragmentManager().getBackStackEntryCount();
        if(count>=1){
            getFragmentManager().popBackStack();
            return;
        }
        super.onBackPressed();
    }
}
