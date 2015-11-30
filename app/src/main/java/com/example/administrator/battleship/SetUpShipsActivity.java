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

    Bitmap userGrid;
    Bitmap battleship;
    Bitmap carrier;
    Bitmap destroyer;
    Bitmap submarine;
    Bitmap ptBoat;
    float width;
    float height;
    float[] shipsX = new float[5];
    float[] shipsY = new float[5];



    public SetUpShipsActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        userGrid = BitmapFactory.decodeResource(getResources(), R.mipmap.blue_square_grid);
        width=height=0;
        shipsX[0] = 200;
        shipsY[0] = 200;
        shipsX[1] = 0;
        shipsY[1] = 0;
        shipsX[2] = 400;
        shipsY[2] = 400;
        shipsX[3] = 0;
        shipsY[3] = 500;
        shipsX[4] = 400;
        shipsY[4] = 600;
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

        battleship = BitmapFactory.decodeResource(getResources(), R.drawable.battleship_horizontal);
        canvas.drawBitmap(battleship, shipsX[1], shipsY[1], null);
        carrier = BitmapFactory.decodeResource(getResources(), R.drawable.carrier_horizontal);
        canvas.drawBitmap(carrier, shipsX[0], shipsY[0], null);
        destroyer = BitmapFactory.decodeResource(getResources(), R.drawable.destroyer_horizotnal);
        canvas.drawBitmap(destroyer, shipsX[2], shipsY[2], null);
        submarine = BitmapFactory.decodeResource(getResources(), R.drawable.submarine_horizotnal);
        canvas.drawBitmap(submarine, shipsX[3], shipsY[3], null);
        ptBoat = BitmapFactory.decodeResource(getResources(), R.drawable.boat_horiztonal);
        canvas.drawBitmap(ptBoat, shipsX[4], shipsY[4], null);
    }


}
