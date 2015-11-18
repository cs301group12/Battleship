package com.example.administrator.battleship;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by nathancamacho on 11/18/15.
 */
public class AIBoardSV extends SurfaceView{

    Bitmap userGrid;
    float width;
    float height;
    public AIBoardSV(Context context, AttributeSet attrs) {
        super(context,attrs);
        setWillNotDraw(false);
        userGrid = BitmapFactory.decodeResource(getResources(), R.mipmap.blue_square_grid);
        width=height=0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                canvas.drawBitmap(userGrid, (float) (j * userGrid.getHeight()), (float) (i * userGrid.getWidth()), null);
                canvas.drawBitmap(userGrid, (float) (i * userGrid.getWidth()), (float) (j * userGrid.getHeight()), null);

            }
        }
    }
}
