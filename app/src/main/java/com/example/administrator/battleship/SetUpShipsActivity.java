package com.example.administrator.battleship;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by aljawad18 on 11/23/2015.
 */
public class SetUpShipsActivity extends SurfaceView{

    ImageView carrier;
    Bitmap userGrid;
    float width;
    float height;
    float[] shipsX = new float[5];
    float[] shipsY = new float[5];

    public SetUpShipsActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        userGrid = BitmapFactory.decodeResource(getResources(), R.mipmap.blue_square_grid);
        width=height=0;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {

        for(int i =0; i<10; i++)
        {
            for(int j = 0;j<10;j++) {

                canvas.drawBitmap(userGrid, (float) (j * userGrid.getHeight()), (float) (i * userGrid.getWidth()), null);
                canvas.drawBitmap(userGrid, (float) (i * userGrid.getWidth()), (float) (j * userGrid.getHeight()), null);

            }
        }

        Bitmap battleship = BitmapFactory.decodeResource(getResources(), R.drawable.battleship_horizontal);
        canvas.drawBitmap(battleship, shipsX[1], shipsY[1], null);
    }


}
