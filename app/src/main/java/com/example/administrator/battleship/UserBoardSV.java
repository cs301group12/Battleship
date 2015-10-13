package com.example.administrator.battleship;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;


public class UserBoardSV extends SurfaceView{

    Bitmap userGrid;
    float width;
    float height;
    public UserBoardSV(Context context) {
        super(context);
        setWillNotDraw(false);
        userGrid = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        width=height=0;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        for(int i =0; i<10; i++)
        {
            canvas.drawBitmap(userGrid,height,(float) (i*userGrid.getWidth()),null);
            canvas.drawBitmap(userGrid,(float) (i*userGrid.getWidth()),width,null);
        }
    }
}
