package com.example.administrator.battleship;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * @author Nathan Camacho
 * @author Hashim AlJawad
 * @author Kelson Sipe
 *
 * @version  12/1/2015
 *
 * Description of BoardSV
 * This class draws the user's board in the BattleshipHumanPlayer activity. Also draws the ships on the board.
 * Uses SurfaceView
 *
 */


public class BoardSV extends SurfaceView{

    private Bitmap blueSquare;//grid made up of blue squares
    //vertical and horizontal versions of each ship
    Bitmap carrierH;
    Bitmap carrierV;
    Bitmap battleshipH;
    Bitmap battleshipV;
    Bitmap destroyerH;
    Bitmap destroyerV;
    Bitmap submarineH;
    Bitmap submarineV;
    Bitmap ptBoatH;
    Bitmap ptBoatV;
    private float width;
    private float height;
    public float[] shipsX = new float[5];//x values for ships to be drawn
    public float[] shipsY = new float[5];//y values for ships to be drawn
    public boolean[] shipOrientations = new boolean[5];

    //Constructor
    public BoardSV(Context context, AttributeSet attrs) {
        super(context,attrs);
        setWillNotDraw(false);
        blueSquare = BitmapFactory.decodeResource(getResources(), R.mipmap.blue_square_grid);
        width=height=0;


    }

    /**
     * Description: Method to draw board and ships
     *
     * CAVEAT:
     *
     */
    @Override
    protected void onDraw(Canvas canvas)
    {
        //draw board
        for(int i =0; i<10; i++)
        {
            for(int j = 0;j<10;j++) {

                canvas.drawBitmap(blueSquare, (float) (j * blueSquare.getHeight()), (float) (i * blueSquare.getWidth()), null);
                canvas.drawBitmap(blueSquare, (float) (i * blueSquare.getWidth()), (float) (j * blueSquare.getHeight()), null);

            }
        }

        //Depending on orientation, draw the horizontal or vertical version of a ship at appropriate x and y values
        if(shipOrientations[0] == true) {
            carrierH = BitmapFactory.decodeResource(getResources(), R.drawable.carrier_horizontal);
            canvas.drawBitmap(carrierH, shipsX[0], shipsY[0], null);
        }
        else
        {
            carrierV = BitmapFactory.decodeResource(getResources(), R.drawable.carrier_vertical);
            canvas.drawBitmap(carrierV, shipsX[0], shipsY[0], null);
        }
        if(shipOrientations[1] == true) {
            battleshipH = BitmapFactory.decodeResource(getResources(), R.drawable.battleship_horizontal);
            canvas.drawBitmap(battleshipH, shipsX[1], shipsY[1], null);
        }
        else{
            battleshipV = BitmapFactory.decodeResource(getResources(), R.drawable.battleship_vertical);
            canvas.drawBitmap(battleshipV, shipsX[1], shipsY[1], null);
        }
        if(shipOrientations[2] == true){
            destroyerH = BitmapFactory.decodeResource(getResources(), R.drawable.destroyer_horizontal);
            canvas.drawBitmap(destroyerH, shipsX[2], shipsY[2], null);
        }
        else{
            destroyerV = BitmapFactory.decodeResource(getResources(), R.drawable.destroyer_vertical);
            canvas.drawBitmap(destroyerV, shipsX[2], shipsY[2], null);
        }
        if(shipOrientations[3] == true){
            submarineH = BitmapFactory.decodeResource(getResources(), R.drawable.submarine_horizontal);
            canvas.drawBitmap(submarineH, shipsX[3], shipsY[3], null);
        }
        else {
            submarineV = BitmapFactory.decodeResource(getResources(), R.drawable.submarine_vertical);
            canvas.drawBitmap(submarineV, shipsX[3], shipsY[3], null);
        }
        if(shipOrientations[4] == true){
            ptBoatH = BitmapFactory.decodeResource(getResources(), R.drawable.boat_horizontal);
            canvas.drawBitmap(ptBoatH, shipsX[4], shipsY[4], null);
        }
        else{
            ptBoatV = BitmapFactory.decodeResource(getResources(), R.drawable.boat_vertical);
            canvas.drawBitmap(ptBoatV, shipsX[4], shipsY[4], null);
        }

    }


}
