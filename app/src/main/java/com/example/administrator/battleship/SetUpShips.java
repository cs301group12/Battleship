package com.example.administrator.battleship;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Vibrator;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class SetUpShips extends ActionBarActivity implements View.OnTouchListener,View.OnClickListener,View.OnLongClickListener{

    private SetUpShipsActivity board;
    private LinearLayout top;

    int carrierRow;
    int carrierCol;

    int battleshipRow;
    int battleshipCol;

    int destroyerRow;
    int destroyerCol;

    int submarineRow;
    int submarineCol;

    int boatRow;
    int boatCol;

    Bitmap userGrid;
    Bitmap battleship;
    Bitmap carrier;
    Bitmap destroyer;
    Bitmap submarine;
    Bitmap ptBoat;

    private Button carrierButton;
    private Button battleshipButton;
    private Button destroyerButton;
    private Button submarineButton;
    private Button ptBoatButton;
    private Button mainMenu;
    private Button saveAndPlay;

    private Canvas canvas;

    private boolean moveCarrier = true;
    private boolean moveBattleship = true;
    private boolean moveDestroyer = true;
    private boolean moveSubmarine = true;
    private boolean movePtBoat = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_ships);
        top = (LinearLayout) findViewById(R.id.topView);
        top.setOnTouchListener(this);
        board = (SetUpShipsActivity) findViewById(R.id.board);
        board.setOnTouchListener(this);
        board.setOnLongClickListener(this);

        mainMenu = (Button) findViewById(R.id.mainMenuButton);
        saveAndPlay = (Button) findViewById(R.id.saveAndPlayButton);
        mainMenu.setOnClickListener(this);
        saveAndPlay.setOnClickListener(this);

        carrierButton = (Button) findViewById(R.id.selectCarrier);
        carrierButton.setOnClickListener(this);
        carrierButton.setOnLongClickListener(this);

        battleshipButton = (Button) findViewById(R.id.selectBattleship);
        battleshipButton.setOnClickListener(this);
        battleshipButton.setOnLongClickListener(this);

        destroyerButton = (Button) findViewById(R.id.selectDestroyer);
        destroyerButton.setOnClickListener(this);
        destroyerButton.setOnLongClickListener(this);

        submarineButton = (Button) findViewById(R.id.selectSubmarine);
        submarineButton.setOnClickListener(this);
        submarineButton.setOnLongClickListener(this);

        ptBoatButton = (Button) findViewById(R.id.selectPTBoat);
        ptBoatButton.setOnClickListener(this);
        ptBoatButton.setOnLongClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_up_ships, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            canvas = board.getHolder().lockCanvas();
            float testX = event.getX();
            float testY = event.getY();
           System.out.println("Value of X: " + testX);
           System.out.println("Value of Y: " + testY);

            if (moveCarrier && !moveBattleship && !moveDestroyer && !moveSubmarine && !movePtBoat) {
                    //size of carrier 431.197
                if(board.getCarrierOrientation() == true) {
                    if (event.getX() < 225){
                            board.shipsX[0] = (float) 16.83;
                    } else if (event.getX() < 325){

                            board.shipsX[0] = (float) 112;
                    } else if (event.getX() < 425){

                            board.shipsX[0] = (float) 211;

                    } else if (event.getX() < 525){
                            board.shipsX[0] = (float) 305.6;

                    } else if (event.getX() < 625){
                            board.shipsX[0] = (float) 403;
                    } else if ((event.getX() < 725) || (event.getX() > 725)){
                            board.shipsX[0] = (float) 498;
                    }
                    if (event.getY() < 98){
                            board.shipsY[0] = (float) 21.2;
                    } else if (event.getY() < 196){
                            board.shipsY[0] = (float) 117.39;
                    } else if (event.getY() < 292){
                        board.shipsY[0] = (float) 212.86;
                    } else if (event.getY() < 388){
                        board.shipsY[0] = (float) 307.8;
                    } else if (event.getY() < 484){
                        board.shipsY[0] = (float) 404.8;
                    } else if (event.getY() < 580){
                        board.shipsY[0] = (float) 501.76;
                    } else if (event.getY() < 676){
                        board.shipsY[0] = (float) 595.7;
                    } else if (event.getY() < 772){
                        board.shipsY[0] = (float) 693.2;
                    } else if (event.getY() < 868){
                        board.shipsY[0] = (float) 788.68;
                    } else if ((event.getY() < 964) || (event.getY() > 964)){
                        board.shipsY[0] = (float) 884.6;
                    }
                }
                else{
                    if (event.getX() < 96){
                        board.shipsX[0] = (float) 22.157;
                    } else if (event.getX() < 194){
                        board.shipsX[0] = (float) 118;
                    } else if (event.getX() < 286){
                        board.shipsX[0] = (float) 213.2;
                    } else if (event.getX() < 382){
                        board.shipsX[0] = (float) 310;
                    } else if (event.getX() < 478){
                        board.shipsX[0] = (float) 405.9;
                    } else if (event.getX() < 574){
                        board.shipsX[0] = (float) 501.94;
                    } else if (event.getX() < 670){
                        board.shipsX[0] = (float) 598.5;
                    } else if (event.getX() < 766){
                        board.shipsX[0] = (float) 693.9;
                    } else if (event.getX() < 860){
                        board.shipsX[0] = (float) 789.87;
                    } else if ((event.getX() < 956) || (event.getX() > 956)){
                        board.shipsX[0] = (float) 885.78;
                    }
                    if (event.getY() < 196) {
                        board.shipsY[0] = (float) 12;
                    } else if (event.getY() < 292){
                        board.shipsY[0] = (float) 110.39;
                    } else if (event.getY() < 388){
                        board.shipsY[0] = (float) 205.86;
                    } else if (event.getY() < 484) {
                        board.shipsY[0] = (float) 302.8;
                    } else if (event.getY() < 580){
                        board.shipsY[0] = (float) 396.8;
                    } else if ((event.getY() < 676) || (event.getY() > 676)){
                        board.shipsY[0] = (float) 495.77;
                    }
                }
            }
            else if (!moveCarrier && moveBattleship && !moveDestroyer && !moveSubmarine && !movePtBoat){
                //size of battleship 350.8624
                if(board.getBattleshipOrientation() == true) {
                    if (event.getX() < 225){
                        board.shipsX[1] = (float) 16.83;
                    } else if (event.getX() < 325){
                        board.shipsX[1] = (float) 112;
                    } else if (event.getX() < 425){
                        board.shipsX[1] = (float) 211;
                    } else if (event.getX() < 525){
                        board.shipsX[1] = (float) 305.6;
                    } else if (event.getX() < 625){
                        board.shipsX[1] = (float) 403;
                    } else if (event.getX() < 725){
                        board.shipsX[1] = (float) 498;
                    } else if ((event.getX() < 825) || (event.getX() > 825)){
                        board.shipsX[1] = (float) 594;
                    }
                    if (event.getY() < 98){
                        board.shipsY[1] = (float) 21.2;
                    } else if (event.getY() < 196){
                        board.shipsY[1] = (float) 117.39;
                    } else if (event.getY() < 292){
                        board.shipsY[1] = (float) 212.86;
                    } else if (event.getY() < 388){
                        board.shipsY[1] = (float) 307.8;
                    } else if (event.getY() < 484){
                        board.shipsY[1] = (float) 404.8;
                    } else if (event.getY() < 580){
                        board.shipsY[1] = (float) 501.76;
                    } else if (event.getY() < 676){
                        board.shipsY[1] = (float) 595.7;
                    } else if (event.getY() < 772){
                        board.shipsY[1] = (float) 693.2;
                    } else if (event.getY() < 868){
                        board.shipsY[1] = (float) 788.68;
                    } else if ((event.getY() < 964) || (event.getY() > 964)){
                        board.shipsY[1] = (float) 884.6;
                    }
                }
                else{
                    if (event.getX() < 96){
                        board.shipsX[1] = (float) 22.157;
                    } else if (event.getX() < 194){
                        board.shipsX[1] = (float) 118;
                    } else if (event.getX() < 286){
                        board.shipsX[1] = (float) 213.2;
                    } else if (event.getX() < 382){
                        board.shipsX[1] = (float) 310;
                    } else if (event.getX() < 478){
                        board.shipsX[1] = (float) 405.9;
                    } else if (event.getX() < 574){
                        board.shipsX[1] = (float) 501.94;
                    } else if (event.getX() < 670){
                        board.shipsX[1] = (float) 598.5;
                    } else if (event.getX() < 766){
                        board.shipsX[1] = (float) 693.9;
                    } else if (event.getX() < 860){
                        board.shipsX[1] = (float) 789.87;
                    } else if ((event.getX() < 956) || (event.getX() > 956)){
                        board.shipsX[1] = (float) 885.78;
                    }
                    if (event.getY() < 196) {
                        board.shipsY[1] = (float) 12;
                    } else if (event.getY() < 292){
                        board.shipsY[1] = (float) 110.39;
                    } else if (event.getY() < 388){
                        board.shipsY[1] = (float) 205.86;
                    } else if (event.getY() < 484) {
                        board.shipsY[1] = (float) 302.8;
                    } else if (event.getY() < 580){
                        board.shipsY[1] = (float) 396.8;
                    } else if (event.getY() < 676){
                        board.shipsY[1] = (float) 495.77;
                    } else if ((event.getY() < 772) || (event.getY() > 772)){
                        board.shipsY[1] = (float) 588;
                    }
                }
            }
            else if(!moveCarrier && !moveBattleship && moveDestroyer && !moveSubmarine && !movePtBoat)
            {
                //destroyer size 260.3315
                if(board.getDestroyerOrientation() == true) {
                    if (event.getX() < 145){
                        board.shipsX[2] = (float) 16.83;
                    } else if (event.getX() < 245){
                        board.shipsX[2] = (float) 112;
                    } else if (event.getX() < 345){
                        board.shipsX[2] = (float) 211;
                    } else if (event.getX() < 445){
                        board.shipsX[2] = (float) 305.6;
                    } else if (event.getX() < 545){
                        board.shipsX[2] = (float) 403;
                    } else if (event.getX() < 645){
                        board.shipsX[2] = (float) 498;
                    } else if (event.getX() < 745){
                        board.shipsX[2] = (float) 594;
                    } else if ((event.getX() < 845) || (event.getX() > 845)){
                        board.shipsX[2] = (float) 685;
                    }
                    if (event.getY() < 98){
                        board.shipsY[2] = (float) 21.2;
                    } else if (event.getY() < 196){
                        board.shipsY[2] = (float) 117.39;
                    } else if (event.getY() < 292){
                        board.shipsY[2] = (float) 212.86;
                    } else if (event.getY() < 388){
                        board.shipsY[2] = (float) 307.8;
                    } else if (event.getY() < 484){
                        board.shipsY[2] = (float) 404.8;
                    } else if (event.getY() < 580){
                        board.shipsY[2] = (float) 501.76;
                    } else if (event.getY() < 676){
                        board.shipsY[2] = (float) 595.7;
                    } else if (event.getY() < 772){
                        board.shipsY[2] = (float) 693.2;
                    } else if (event.getY() < 868){
                        board.shipsY[2] = (float) 788.68;
                    } else if ((event.getY() < 964) || (event.getY() > 964)){
                        board.shipsY[2] = (float) 884.6;
                    }
                }
                else{
                    if (event.getX() < 96){
                        board.shipsX[2] = (float) 22.157;
                    } else if (event.getX() < 194){
                        board.shipsX[2] = (float) 118;
                    } else if (event.getX() < 286){
                        board.shipsX[2] = (float) 213.2;
                    } else if (event.getX() < 382){
                        board.shipsX[2] = (float) 310;
                    } else if (event.getX() < 478){
                        board.shipsX[2] = (float) 405.9;
                    } else if (event.getX() < 574){
                        board.shipsX[2] = (float) 501.94;
                    } else if (event.getX() < 670){
                        board.shipsX[2] = (float) 598.5;
                    } else if (event.getX() < 766){
                        board.shipsX[2] = (float) 693.9;
                    } else if (event.getX() < 860){
                        board.shipsX[2] = (float) 789.87;
                    } else if ((event.getX() < 956) || (event.getX() > 956)){
                        board.shipsX[2] = (float) 885.78;
                    }
                    if (event.getY() < 196) {
                        board.shipsY[2] = (float) 12;
                    } else if (event.getY() < 292){
                        board.shipsY[2] = (float) 110.39;
                    } else if (event.getY() < 388){
                        board.shipsY[2] = (float) 205.86;
                    } else if (event.getY() < 484) {
                        board.shipsY[2] = (float) 302.8;
                    } else if (event.getY() < 580){
                        board.shipsY[2] = (float) 396.8;
                    } else if (event.getY() < 676){
                        board.shipsY[2] = (float) 495.77;
                    } else if (event.getY() < 772){
                        board.shipsY[2] = (float) 588;
                    } else if ((event.getY() < 768) || (event.getY() > 768)){
                        board.shipsY[2] = (float) 684;
                    }
                }
            }
            else if(!moveCarrier && !moveBattleship && !moveDestroyer && moveSubmarine && !movePtBoat)
            {
                //submarine size 260.3315
                if(board.getSubmarineOrientation() == true) {
                    if (event.getX() < 145){
                        board.shipsX[3] = (float) 16.83;
                    } else if (event.getX() < 245){
                        board.shipsX[3] = (float) 112;
                    } else if (event.getX() < 345){
                        board.shipsX[3] = (float) 211;
                    } else if (event.getX() < 445){
                        board.shipsX[3] = (float) 305.6;
                    } else if (event.getX() < 545){
                        board.shipsX[3] = (float) 403;
                    } else if (event.getX() < 645){
                        board.shipsX[3] = (float) 498;
                    } else if (event.getX() < 745){
                        board.shipsX[3] = (float) 594;
                    } else if ((event.getX() < 845) || (event.getX() > 845)){
                        board.shipsX[3] = (float) 685;
                    }
                    if (event.getY() < 98){
                        board.shipsY[3] = (float) 21.2;
                    } else if (event.getY() < 196){
                        board.shipsY[3] = (float) 117.39;
                    } else if (event.getY() < 292){
                        board.shipsY[3] = (float) 212.86;
                    } else if (event.getY() < 388){
                        board.shipsY[3] = (float) 307.8;
                    } else if (event.getY() < 484){
                        board.shipsY[3] = (float) 404.8;
                    } else if (event.getY() < 580){
                        board.shipsY[3] = (float) 501.76;
                    } else if (event.getY() < 676){
                        board.shipsY[3] = (float) 595.7;
                    } else if (event.getY() < 772){
                        board.shipsY[3] = (float) 693.2;
                    } else if (event.getY() < 868){
                        board.shipsY[3] = (float) 788.68;
                    } else if ((event.getY() < 964) || (event.getY() > 964)){
                        board.shipsY[3] = (float) 884.6;
                    }
                }
                else{
                    if (event.getX() < 96){
                        board.shipsX[3] = (float) 22.157;
                    } else if (event.getX() < 194){
                        board.shipsX[3] = (float) 118;
                    } else if (event.getX() < 286){
                        board.shipsX[3] = (float) 213.2;
                    } else if (event.getX() < 382){
                        board.shipsX[3] = (float) 310;
                    } else if (event.getX() < 478){
                        board.shipsX[3] = (float) 405.9;
                    } else if (event.getX() < 574){
                        board.shipsX[3] = (float) 501.94;
                    } else if (event.getX() < 670){
                        board.shipsX[3] = (float) 598.5;
                    } else if (event.getX() < 766){
                        board.shipsX[3] = (float) 693.9;
                    } else if (event.getX() < 860){
                        board.shipsX[3] = (float) 789.87;
                    } else if ((event.getX() < 956) || (event.getX() > 956)){
                        board.shipsX[3] = (float) 885.78;
                    }
                    if (event.getY() < 196) {
                        board.shipsY[3] = (float) 12;
                    } else if (event.getY() < 292){
                        board.shipsY[3] = (float) 110.39;
                    } else if (event.getY() < 388){
                        board.shipsY[3] = (float) 205.86;
                    } else if (event.getY() < 484) {
                        board.shipsY[3] = (float) 302.8;
                    } else if (event.getY() < 580){
                        board.shipsY[3] = (float) 396.8;
                    } else if (event.getY() < 676){
                        board.shipsY[3] = (float) 495.77;
                    } else if (event.getY() < 772){
                        board.shipsY[3] = (float) 588;
                    } else if ((event.getY() < 868) || (event.getY() > 868)){
                        board.shipsY[3] = (float) 684;
                    }
                }
            }
            else if(!moveCarrier && !moveBattleship && !moveDestroyer && !moveSubmarine && movePtBoat)
            {
                //size of boat 176.81208
                if(board.getpTBoatOrientation() == true) {
                    if (event.getX() < 125){
                        board.shipsX[4] = (float) 10;
                    } else if (event.getX() < 225){
                        board.shipsX[4] = (float) 106;
                    } else if (event.getX() < 325){
                        board.shipsX[4] = (float) 205;
                    } else if (event.getX() < 425){
                        board.shipsX[4] = (float) 298;
                    } else if (event.getX() < 525){
                        board.shipsX[4] = (float) 396;
                    } else if (event.getX() < 625){
                        board.shipsX[4] = (float) 492;
                    } else if (event.getX() < 725){
                        board.shipsX[4] = (float) 588;
                    } else if (event.getX() < 825){
                        board.shipsX[4] = (float) 680;
                    } else if ((event.getX() < 925) || (event.getX() > 925)){
                        board.shipsX[4] = (float) 776;
                    }

                    if (event.getY() < 98){
                        board.shipsY[4] = (float) 21.2;
                    } else if (event.getY() < 196){
                        board.shipsY[4] = (float) 117.39;
                    } else if (event.getY() < 292){
                        board.shipsY[4] = (float) 212.86;
                    } else if (event.getY() < 388){
                        board.shipsY[4] = (float) 307.8;
                    } else if (event.getY() < 484){
                        board.shipsY[4] = (float) 404.8;
                    } else if (event.getY() < 580){
                        board.shipsY[4] = (float) 501.76;
                    } else if (event.getY() < 676){
                        board.shipsY[4] = (float) 595.7;
                    } else if (event.getY() < 772){
                        board.shipsY[4] = (float) 693.2;
                    } else if (event.getY() < 868){
                        board.shipsY[4] = (float) 788.68;
                    } else if ((event.getY() < 964) || (event.getY() > 964)){
                        board.shipsY[4] = (float) 884.6;
                    }
                }
                else{
                    if (event.getX() < 96){
                        board.shipsX[4] = (float) 22.157;
                    } else if (event.getX() < 194){
                        board.shipsX[4] = (float) 118;
                    } else if (event.getX() < 286){
                        board.shipsX[4] = (float) 213.2;
                    } else if (event.getX() < 382){
                        board.shipsX[4] = (float) 310;
                    } else if (event.getX() < 478){
                        board.shipsX[4] = (float) 405.9;
                    } else if (event.getX() < 574){
                        board.shipsX[4] = (float) 501.94;
                    } else if (event.getX() < 670){
                        board.shipsX[4] = (float) 598.5;
                    } else if (event.getX() < 766){
                        board.shipsX[4] = (float) 693.9;
                    } else if (event.getX() < 860){
                        board.shipsX[4] = (float) 789.87;
                    } else if ((event.getX() < 956) || (event.getX() > 956)){
                        board.shipsX[4] = (float) 885.78;
                    }
                    if (event.getY() < 196) {
                        board.shipsY[4] = (float) 9;
                    } else if (event.getY() < 292){
                        board.shipsY[4] = (float) 105;
                    } else if (event.getY() < 388){
                        board.shipsY[4] = (float) 201;
                    } else if (event.getY() < 484) {
                        board.shipsY[4] = (float) 298;
                    } else if (event.getY() < 580){
                        board.shipsY[4] = (float) 390;
                    } else if (event.getY() < 676){
                        board.shipsY[4] = (float) 490;
                    } else if (event.getY() < 772){
                        board.shipsY[4] = (float) 580;
                    } else if (event.getY() < 868){
                        board.shipsY[4] = (float) 680;
                    } else if ((event.getY() < 962) || (event.getY() > 962)){
                        board.shipsY[4] = (float) 772;
                    }
                }
            }

            board.getHolder().unlockCanvasAndPost(canvas);
            board.postInvalidate();
        }

        if(event.getAction() == MotionEvent.ACTION_UP)
        {

        }
        return true;

    }


    public int getRow(int y)
    {
        //System.out.println("method " + y);
        if(y == 21 || y == 12 || y == 20 || y == 9) { return 1; }
        else if(y == 117 || y == 110 || y == 105) { return 2; }
        else if(y == 212 || y == 210 || y == 205 || y == 201) { return 3; }
        else if(y == 307 || y == 302 || y == 298) { return 4; }
        else if(y == 404 || y == 400 || y == 396 || y == 390) { return 5; }
        else if(y == 501 || y == 500 || y == 495 || y == 490){ return 6; }
        else if(y == 595 || y == 600 || y == 588 || y == 580) { return 7; }
        else if(y == 693 || y == 684 || y == 680) { return 8; }
        else if(y == 788 || y == 772) { return 9; }
        else if(y == 884) { return 10;}
        else { return 0; }
    }

    public int getCol(int x)
    {
        //System.out.println("method " + x);
        if(x == 0 || x == 16 || x == 22 || x == 10) { return 1; }
        else if(x == 112 || x == 118 || x == 106) { return 2; }
        else if(x == 200 || x == 211 || x == 213 || x == 205) { return 3; }
        else if(x == 305 || x == 310 || x == 298) { return 4; }
        else if(x == 400 || x == 403 || x == 405 || x == 396) { return 5; }
        else if(x == 498 || x == 501 || x == 492){ return 6; }
        else if(x == 589 || x == 594 || x == 598 || x == 588) { return 7; }
        else if(x == 693 || x == 685 || x == 680) { return 8; }
        else if(x == 789 || x == 776) { return 9; }
        else if(x == 885) { return 10;}
        else { return 0; }

    }

    public boolean checkOverLapping(int ship1Num, int ship2Num,int row1, int col1,int row2,int col2)
    {
        //TODO
      return false;
    }
    @Override
    public void onClick(View view)
    {
        if(view == carrierButton){
            moveCarrier = true;
            moveBattleship = false;
            moveDestroyer = false;
            moveSubmarine = false;
            movePtBoat = false;
        }
        if (view == battleshipButton){
            moveCarrier = false;
            moveBattleship = true;
            moveDestroyer = false;
            moveSubmarine = false;
            movePtBoat = false;
        }
        if (view == destroyerButton) {
            moveCarrier = false;
            moveBattleship = false;
            moveDestroyer = true;
            moveSubmarine = false;
            movePtBoat = false;
        }
        if (view == submarineButton){
            moveCarrier = false;
            moveBattleship = false;
            moveDestroyer = false;
            moveSubmarine = true;
            movePtBoat = false;
        }
        if (view == ptBoatButton){
            moveCarrier = false;
            moveBattleship = false;
            moveDestroyer = false;
            moveSubmarine = false;
            movePtBoat = true;
        }
        if (view == mainMenu){
            finish();
        }
        if (view == saveAndPlay){
            Intent intent = new Intent(this, BattleshipHumanPlayer.class);
            intent.putExtra("Ships X",board.shipsX);
            intent.putExtra("Ships Y",board.shipsY);
            intent.putExtra("Ship Orientations",board.shipOrientations);

            carrierRow = getRow((int) board.shipsY[0]);
            carrierCol = getCol((int) board.shipsX[0]);

            battleshipRow = getRow((int) board.shipsY[1]);
            battleshipCol = getCol((int) board.shipsX[1]);

            destroyerRow = getRow((int) board.shipsY[2]);
            destroyerCol = getCol((int) board.shipsX[2]);

            submarineRow = getRow((int) board.shipsY[3]);
            submarineCol = getCol((int) board.shipsX[3]);

            boatRow = getRow((int) board.shipsY[4]);
            boatCol = getCol((int)board.shipsX[4]);

            int[] shipsVals = new int[10];

            shipsVals[0] = carrierRow;
            shipsVals[1] = carrierCol;

            shipsVals[2] = battleshipRow;
            shipsVals[3] = battleshipCol;

            shipsVals[4] = destroyerRow;
            shipsVals[5] = destroyerCol;

            shipsVals[6] = submarineRow;
            shipsVals[7] = submarineCol;

            shipsVals[8] = boatRow;
            shipsVals[9] = boatCol;

            intent.putExtra("Ship Set Up",shipsVals);

            startActivityForResult(intent,10);
        }
    }

    @Override
    public boolean onLongClick(View v) {

        if(v == carrierButton) {
            boolean dummy = board.getCarrierOrientation();
            board.setCarrierOrientation(!dummy);
            Toast.makeText(getApplicationContext(),"Carrier Orientation Has Been Changed.",Toast.LENGTH_SHORT).show();
            return true;
        }
        if(v == battleshipButton) {
            boolean dummy2 = board.getBattleshipOrientation();
            board.setBattleshipOrientation(!dummy2);
            Toast.makeText(getApplicationContext(),"Battleship Orientation Has Been Changed.",Toast.LENGTH_SHORT).show();
            return true;
        }
        if(v == destroyerButton){
            boolean dummy3 = board.getDestroyerOrientation();
            board.setDestroyerOrientation(!dummy3);
            Toast.makeText(getApplicationContext(),"Destroyer Orientation Has Been Changed.",Toast.LENGTH_SHORT).show();
            return true;
        }
        if(v == submarineButton){
            boolean dummy4 = board.getSubmarineOrientation();
            board.setSubmarineOrientation(!dummy4);
            Toast.makeText(getApplicationContext(),"Submarine Orientation Has Been Changed.",Toast.LENGTH_SHORT).show();
            return true;
        }
        if(v == ptBoatButton){
            boolean dummy5 = board.getpTBoatOrientation();
            board.setpTBoatOrientation(!dummy5);
            Toast.makeText(getApplicationContext(),"Patrol Boat Orientation Has Been Changed.",Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
