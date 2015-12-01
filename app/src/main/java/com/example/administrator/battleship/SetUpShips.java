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

    Bitmap userGrid;
    Bitmap battleship;
    Bitmap carrier;
    Bitmap destroyer;
    Bitmap submarine;
    Bitmap ptBoat;

    GestureDetector mGestureDetector;



    private GestureDetector myDetector = null;

    private Button carrierButton;
    private Button battleshipButton;
    private Button destroyerButton;
    private Button submarineButton;
    private Button ptBoatButton;

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

        goToMainMenu();
        playGame();

        //goToPlayGame = (Button) findViewById(R.id.)

    }

    private void goToMainMenu(){
        Button mainMenu = (Button) findViewById(R.id.mainMenuButton);
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetUpShips.this, MainActivity.class));
            }
        });
    }

    private void playGame(){
        Button saveAndPlay = (Button) findViewById(R.id.saveAndPlayButton);
        saveAndPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetUpShips.this, BattleshipHumanPlayer.class));
            }
        });
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
                if (board.getCarrierOrientation() == true) {
                    if (event.getX() > 523) {
                        board.shipsX[0] = 523;
                    } else if (event.getX() < 7.05) {
                        board.shipsX[0] = (float) 7.05;
                    } else {
                        board.shipsX[0] = event.getX();
                    }
                    if (event.getY() < 4.93) {
                        board.shipsY[0] = (float) 14.926;
                    } else if (event.getY() > 897.13) {
                        board.shipsY[0] = (float) 884.1438;
                    } else {
                        board.shipsY[0] = event.getY();
                    }
                }
                else{
                    if (event.getX() > 885) {
                        board.shipsX[0] = 885;
                    } else if (event.getX() < 15) {
                        board.shipsX[0] = (float) 15;
                    } else {
                        board.shipsX[0] = event.getX();
                    }
                    if (event.getY() < 12) {
                        board.shipsY[0] = (float) 12;
                    } else if (event.getY() > 511) {
                        board.shipsY[0] = (float) 511;
                    } else {
                        board.shipsY[0] = event.getY();
                    }
                }
            }
            else if (!moveCarrier && moveBattleship && !moveDestroyer && !moveSubmarine && !movePtBoat){

                //size of battleship 350.8624
                if(board.getBattleshipOrientation() == true) {
                    if (event.getX() > 605.9) {
                        board.shipsX[1] = (float) 605.9;
                    } else if (event.getX() < 4.93) {
                        board.shipsX[1] = (float) 4.93;
                    } else {
                        board.shipsX[1] = event.getX();
                    }
                    if (event.getY() < 6.92) {
                        board.shipsY[1] = (float) 21.424667;
                    } else if (event.getY() > 900.1) {
                        board.shipsY[1] = (float) 887.6427;
                    } else {
                        board.shipsY[1] = event.getY();
                    }
                }
                else{
                    if (event.getX() > 885) {
                        board.shipsX[1] = (float) 885;
                    } else if (event.getX() < 15) {
                        board.shipsX[1] = (float) 15;
                    } else {
                        board.shipsX[1] = event.getX();
                    }
                    if (event.getY() < 12) {
                        board.shipsY[1] = (float) 12;
                    } else if (event.getY() > 588) {
                        board.shipsY[1] = (float) 588;
                    } else {
                        board.shipsY[1] = event.getY();
                    }
                }
            }
            else if(!moveCarrier && !moveBattleship && moveDestroyer && !moveSubmarine && !movePtBoat)
            {
                //destroyer size 260.3315
                if(board.destroyerOrientation == true) {
                    if (event.getX() > 692.977) {
                        board.shipsX[2] = (float) 692.977;
                    } else if (event.getX() < 4.93) {
                        board.shipsX[2] = (float) 4.93;
                    } else {
                        board.shipsX[2] = event.getX();
                    }
                    if (event.getY() < 6.92) {
                        board.shipsY[2] = (float) 23.549;
                    } else if (event.getY() > 901.9) {
                        board.shipsY[2] = (float) 886.64294;
                    } else {
                        board.shipsY[2] = event.getY();
                    }
                }
                else {
                if (event.getX() > 890) {
                    board.shipsX[2] = (float) 890;
                } else if (event.getX() < 15) {
                    board.shipsX[2] = (float) 15;
                } else {
                    board.shipsX[2] = event.getX();
                }
                if (event.getY() < 12) {
                    board.shipsY[2] = (float) 12;
                } else if (event.getY() > 690) {
                    board.shipsY[2] = (float) 690;
                } else {
                    board.shipsY[2] = event.getY();
                }
            }
            }
            else if(!moveCarrier && !moveBattleship && !moveDestroyer && moveSubmarine && !movePtBoat)
            {
                //submarine size 260.3315
                if (board.submarineOrientation == true) {
                    if (event.getX() > 692.977) {
                        board.shipsX[3] = (float) 692.977;
                    } else if (event.getX() < 4.93) {
                        board.shipsX[3] = (float) 4.93;
                    } else {
                        board.shipsX[3] = event.getX();
                    }
                    if (event.getY() < 6.92) {
                        board.shipsY[3] = (float) 23.549;
                    } else if (event.getY() > 901.9) {
                        board.shipsY[3] = (float) 886.64294;
                    } else {
                        board.shipsY[3] = event.getY();
                    }
                }
                else{
                    if (event.getX() > 890) {
                        board.shipsX[3] = (float) 890;
                    } else if (event.getX() < 15) {
                        board.shipsX[3] = (float) 15;
                    } else {
                        board.shipsX[3] = event.getX();
                    }
                    if (event.getY() < 12) {
                        board.shipsY[3] = (float) 12;
                    } else if (event.getY() > 690) {
                        board.shipsY[3] = (float) 690;
                    } else {
                        board.shipsY[3] = event.getY();
                    }
                }
            }
            else if(!moveCarrier && !moveBattleship && !moveDestroyer && !moveSubmarine && movePtBoat)
            {
                //size of boat 176.81208
                if (board.pTBoatOrientation == true) {
                    if (event.getX() > 778.2733) {
                        board.shipsX[4] = (float) 778.27337;
                    } else if (event.getX() < 4.93) {
                        board.shipsX[4] = (float) 4.93;
                    } else {
                        board.shipsX[4] = event.getX();
                    }
                    if (event.getY() < 6.92) {
                        board.shipsY[4] = (float) 18.925476;
                    } else if (event.getY() > 901.9) {
                        board.shipsY[4] = (float) 888.14246;
                    } else {
                        board.shipsY[4] = event.getY();
                    }
                }
                else {
                    if (event.getX() > 890) {
                        board.shipsX[4] = (float) 890;
                    } else if (event.getX() < 15) {
                        board.shipsX[4] = (float) 15;
                    } else {
                        board.shipsX[4] = event.getX();
                    }
                    if (event.getY() < 9) {
                        board.shipsY[4] = (float) 9;
                    } else if (event.getY() > 776) {
                        board.shipsY[4] = (float) 776;
                    } else {
                        board.shipsY[4] = event.getY();
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

    /*
    public boolean checkOverlapping(int shipNum) {
        if (shipNum == 0) {
            float size = (float) (board.getX() + 431.197);
            for(int i = 0; i < 4; i++)
            {
                if(board.shipsX[0] > board.shipsX[i] && (board.shipsX[0]+size) < board.shipsX[i]  && i != shipNum)
                {
                    board.shipsX[0] =
                }
            }
        } else if (shipNum == 1) {
            float ship = (float) (board.getX() + 350.8624);
        } else if (shipNum == 2)
        {
            float ship = (float) (board.getX() + 260.3315);
        }
        else if(shipNum == 3)
        {
            float ship = (float) (board.getX() + 260.3315);
        }
        else if(shipNum == 4)
        {
            float ship = (float) (board.getX() + 176.81208);
        }
        else
        {
            return false;
        }

        for(int i = 0; i < 4; i++)
        {

        }
    }
    */

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
    }

    @Override
    public boolean onLongClick(View v) {

        if(v == carrierButton) {
            boolean dummy = board.getCarrierOrientation();
            board.setCarrierOrientation(!dummy);
            return true;
        }
        if(v == battleshipButton) {
            boolean dummy2 = board.getBattleshipOrientation();
            board.setBattleshipOrientation(!dummy2);
            return true;
        }
        if(v == destroyerButton){
            boolean dummy3 = board.getDestroyerOrientation();
            board.setDestroyerOrientation(!dummy3);
            return true;
        }
        if(v == submarineButton){
            boolean dummy4 = board.getSubmarineOrientation();
            board.setSubmarineOrientation(!dummy4);
            return true;
        }
        if(v == ptBoatButton){
            boolean dummy5 = board.getpTBoatOrientation();
            board.setpTBoatOrientation(!dummy5);
            return true;
        }
        return false;
    }
}
