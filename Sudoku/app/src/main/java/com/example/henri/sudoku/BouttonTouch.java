package com.example.henri.sudoku;

import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Henri on 24/03/2018.
 */

public class BouttonTouch implements View.OnTouchListener {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(v.getId()){
            case 1:
                Log.d("Bouton 1", " "+ v.getId());
                break;
            case 2:
                Log.d("Bouton 2", " "+ v.getId());
                break;
            case 3:
                Log.d("Bouton 3", " "+ v.getId());
                break;
            case 4:
                Log.d("Bouton 4", " "+ v.getId());
                break;
        }
        return true;
    }
}
