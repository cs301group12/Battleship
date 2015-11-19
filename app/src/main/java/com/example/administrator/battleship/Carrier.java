package com.example.administrator.battleship;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by nathancamacho on 11/18/15.
 */
public class Carrier extends UserShips {

    float x;
    float y;

    public Carrier(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    @Override
    public void onDraw(Canvas canvas){

    }
}
