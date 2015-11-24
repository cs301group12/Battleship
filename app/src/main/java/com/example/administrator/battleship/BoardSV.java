package com.example.administrator.battleship;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;


public class BoardSV extends SurfaceView{

    Bitmap userGrid;
    float width;
    float height;
    float[] shipsX = new float[5];
    float[] shipsY = new float[5];
    public BoardSV(Context context, AttributeSet attrs) {
        super(context,attrs);
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

        Paint carrierColor = new Paint();
        carrierColor.setColor(Color.GRAY);
        canvas.drawRect(300+carrierX, 200+carrierY, 500, 300,carrierColor );
    }


}
