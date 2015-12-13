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
 * @author Nathan Camacho
 * @author Hashim AlJawad
 * @author Kelson Sipe
 *
 * @version  12/12/2015
 *
 * Description of SetUpShipsActivity class:
 * SetUpShipsActivity draws the user ships on the grid.
 *
 *
 **/

public class SetUpShipsActivity extends SurfaceView{

    //Initializes image variables and horizontal for all ships to true
    private Bitmap userGrid;
    private Bitmap carrierH;    //Horizontal Carrier
    private Bitmap carrierV;    //Vertical Carrier
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
    public float[] shipsX = new float[5];   //Array for the x coordinate for each ship
    public float[] shipsY = new float[5];   //Array for the y coordinate for each ship
    public boolean[] shipOrientations = new boolean[5]; //Array for whether each ship is horizontal
    public boolean carrierOrientation = true;       //Initializes each ship to be horizontal
    public boolean battleshipOrientation = true;
    public boolean destroyerOrientation = true;
    public boolean submarineOrientation = true;
    public boolean pTBoatOrientation = true;

    /** Draws the ships horizontally onto a default place on the user grid
     *
     * @param context
     * @param attrs
     */
    public SetUpShipsActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        userGrid = BitmapFactory.decodeResource(getResources(), R.mipmap.blue_square_grid);
        width=height=0;
        shipsX[0] = 200; //Carrier
        shipsY[0] = 210;
        shipsX[1] = 0;   //Battleship
        shipsY[1] = 20;
        shipsX[2] = 400; //Destroyer
        shipsY[2] = 400;
        shipsX[3] = 0;   //Submarine
        shipsY[3] = 500;
        shipsX[4] = 400; //PTBoat
        shipsY[4] = 600;
        shipOrientations[0] = carrierOrientation;
        shipOrientations[1] = battleshipOrientation;
        shipOrientations[2] = destroyerOrientation;
        shipOrientations[3] = submarineOrientation;
        shipOrientations[4] = pTBoatOrientation;
    }

    /**
     * Description: Draws the user grid. Gets whether the ship is horizontal so that if horizontal is false, vertical will be true
     *
     * @param canvas
     */
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

        // Checks if a ship is Horizontal, and sets the canvas to draw the ships in the appropriate orientation.
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

    /**Sets and Returns the orientation of each ship
     *
     * @param orientation
     */
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

    /**Draws the ships on the user grid and checks if a ship is out of bounds
     *
     * @param nameOfShip
     * @param shipNum
     * @param imageID
     * @param canvas
     * @param isHorizontal
     */
    public void drawShip(Bitmap nameOfShip,int shipNum,int imageID,Canvas canvas,boolean isHorizontal)
    {
        if(imageID == 1) {
            if(isHorizontal == true) {
                checkOutOfBoundsX(0);
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.carrier_horizontal);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
            }
            else
            {
                checkOutOfBoundsY(0);
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.carrier_vertical);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
            }
        } else if (imageID == 2) {
            if (isHorizontal == true) {
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.battleship_horizontal);
                checkOutOfBoundsX(1);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
                System.out.println("HORZ X: " + shipsX[shipNum]);
                System.out.println("HORZ Y: " + shipsY[shipNum]);
            } else
            {
                checkOutOfBoundsY(1);
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.battleship_vertical);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
                System.out.println("VER X: " + shipsX[shipNum]);
                System.out.println("VER Y: " + shipsY[shipNum]);
            }
        }
        else if(imageID == 3)
        {
            if(isHorizontal == true) {
                checkOutOfBoundsX(2);
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.destroyer_horizontal);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
            }
            else
            {
                checkOutOfBoundsY(2);
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.destroyer_vertical);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
            }
        }
        else if(imageID == 4)
        {
            if(isHorizontal == true) {
                checkOutOfBoundsX(3);
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.submarine_horizontal);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
            }
            else
            {
                checkOutOfBoundsY(3);
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.submarine_vertical);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
            }
        }
        else if(imageID == 5)
        {
            if(isHorizontal == true) {
                checkOutOfBoundsX(4);
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.boat_horizontal);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
            }
            else
            {
                checkOutOfBoundsY(4);
                nameOfShip = BitmapFactory.decodeResource(getResources(), R.drawable.boat_vertical);
                canvas.drawBitmap(nameOfShip, shipsX[shipNum], shipsY[shipNum], null);
            }
        }
    }


    //Returns each ships x and y coordinates
    public float[] getShipsX(){
        return shipsX;
    }
    public float[] getShipsY(){
        return shipsY;
    }

    /** Redraws an out of bounds ship so that it is no longer out of bounds
     *
     * @param shipNum
     */
    public void checkOutOfBoundsX(int shipNum)
    {
        if(shipNum == 0) { if (shipsX[shipNum] > 498) { shipsX[shipNum] = 498; } }
        else if(shipNum == 1) { if (shipsX[shipNum] > 594) { shipsX[shipNum] = 594; } }
        else if(shipNum == 2) { if (shipsX[shipNum] > 685) { shipsX[shipNum] = 685; } }
        else if(shipNum == 3) { if (shipsX[shipNum] > 685) { shipsX[shipNum] = 685; } }
        else if(shipNum == 4) { if (shipsX[shipNum] > 776) { shipsX[shipNum] = 776; } }
        else { return; }
    }

    public void checkOutOfBoundsY(int shipNum)
    {
        if(shipNum == 0) { if (shipsY[shipNum] > 498) { shipsY[shipNum] = 498; } }
        else if(shipNum == 1) { if (shipsY[shipNum] > 594) { shipsY[shipNum] = 594; } }
        else if(shipNum == 2) { if (shipsY[shipNum] > 685) { shipsY[shipNum] = 685; } }
        else if(shipNum == 3) { if (shipsY[shipNum] > 685) { shipsY[shipNum] = 685; } }
        else if(shipNum == 4) { if (shipsY[shipNum] > 776) { shipsY[shipNum] = 776; } }
        else { return; }
    }

}
