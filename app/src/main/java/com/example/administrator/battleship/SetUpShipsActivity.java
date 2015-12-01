package com.example.administrator.battleship;

import android.content.Context;
import android.gesture.Gesture;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by aljawad18 on 11/23/2015.
 */
public class SetUpShipsActivity extends SurfaceView{

    private Bitmap userGrid;
    private Bitmap carrierH;
    private Bitmap carrierV;
    private Bitmap battleshipH;
    private Bitmap battleshipV;
    private Bitmap destroyerH;
    private Bitmap destroyerV;
    private Bitmap submarineH;
    private Bitmap submarineV;
    private Bitmap ptBoatH;
    private Bitmap ptBoatV;
    private float width;
    private float height;
    public float[] shipsX = new float[5];
    public float[] shipsY = new float[5];
    public boolean[] shipOrientations = new boolean[5];
    public boolean carrierOrientation = true;
    public boolean battleshipOrientation = true;
    public boolean destroyerOrientation = true;
    public boolean submarineOrientation = true;
    public boolean pTBoatOrientation = true;

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
        shipOrientations[0] = carrierOrientation;
        shipOrientations[1] = battleshipOrientation;
        shipOrientations[2] = destroyerOrientation;
        shipOrientations[3] = submarineOrientation;
        shipOrientations[4] = pTBoatOrientation;


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

        /*
        drawShip(carrier, 0, 1, canvas, true);
        drawShip(carrier, 0, 1, canvas, false);
        drawShip(battleship, 1, 2, canvas, true);
        drawShip(destroyer, 2, 3, canvas, true);
        drawShip(submarine, 3, 4, canvas, true);
        drawShip(ptBoat,4,5,canvas,true);
        */

        boolean dummy = getCarrierOrientation();
        if(dummy == true) {
            drawShip(carrierH, 0, 1, canvas, true);
        }
        else
        {
            drawShip(carrierV, 0, 1, canvas, false);
        }
        boolean dummy2 = getBattleshipOrientation();
        if(dummy2 == true) {
            drawShip(battleshipH, 1, 2, canvas, true);
        }
        else{
            drawShip(battleshipV, 1, 2, canvas, false);
        }
        boolean dummy3 = getDestroyerOrientation();
        if(dummy3 == true){
            drawShip(destroyerH, 2, 3, canvas, true);
        }
        else{
            drawShip(destroyerV, 2, 3, canvas, false);
        }
        boolean dummy4 = getSubmarineOrientation();
        if(dummy4 == true){
            drawShip(submarineH, 3, 4, canvas, true);
        }
        else {
            drawShip(submarineV, 3, 4, canvas, false);
        }
        boolean dummy5 = getpTBoatOrientation();
        if(dummy5 == true){
            drawShip(ptBoatH, 4, 5, canvas, true);
        }
        else{
            drawShip(ptBoatV, 4, 5, canvas, false);
        }
    }

    public void setCarrierOrientation(boolean orientation){
        carrierOrientation = orientation;
        shipOrientations[0] = carrierOrientation;
    }
    public boolean getCarrierOrientation() {return carrierOrientation;}
    public void setBattleshipOrientation(boolean orientation){
        battleshipOrientation = orientation;
        shipOrientations[1] = battleshipOrientation;
    }
    public boolean getBattleshipOrientation() {return battleshipOrientation;}
    public void setDestroyerOrientation(boolean orientation){
        destroyerOrientation = orientation;
        shipOrientations[2] = destroyerOrientation;
    }
    public boolean getDestroyerOrientation(){return destroyerOrientation;}
    public void setSubmarineOrientation(boolean orientation){
        submarineOrientation = orientation;
        shipOrientations[3] = submarineOrientation;
    }
    public boolean getSubmarineOrientation(){return submarineOrientation;}
    public void setpTBoatOrientation(boolean orientation){
        pTBoatOrientation = orientation;
        shipOrientations[4] = pTBoatOrientation;
    }
    public boolean getpTBoatOrientation(){return pTBoatOrientation;}


    public void drawShip(Bitmap nameOfShip,int shipNum,int imageID,Canvas canvas,boolean isHorizontal)
    {
        if(imageID == 1) {
            if(isHorizontal == true) {
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.carrier_horizontal);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
            }
            else
            {
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.carrier_vertical);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
            }
        }
        else if(imageID == 2)
        {
            if(isHorizontal == true) {
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.battleship_horizontal);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
            }
            else
            {
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.battleship_vertical);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
            }
        }
        else if(imageID == 3)
        {
            if(isHorizontal == true) {
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.destroyer_horizontal);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
            }
            else
            {
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.destroyer_vertical);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
            }
        }
        else if(imageID == 4)
        {
            if(isHorizontal == true) {
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.submarine_horizontal);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
            }
            else
            {
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.submarine_vertical);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
            }
        }
        else if(imageID == 5)
        {
            if(isHorizontal == true) {
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.boat_horizontal);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
            }
            else
            {
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.boat_vertical);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
            }
        }
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public float[] getShipsX(){
        return shipsX;
    }
    public float[] getShipsY(){
        return shipsY;
    }


}
