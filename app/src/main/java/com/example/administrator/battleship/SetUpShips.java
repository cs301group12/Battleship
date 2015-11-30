package com.example.administrator.battleship;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
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


public class SetUpShips extends ActionBarActivity implements View.OnTouchListener,View.OnClickListener {

    private SetUpShipsActivity board;
    private LinearLayout top;
    ImageView carrierImageHor;
    ImageView battleshipImageHor;
    ImageView destroyerImageHor;
    ImageView submarineImageHor;
    ImageView patrolBoatImageHor;

    GestureDetector mGestureDetector;

    ShipImage carrier;
    ShipImage battleship;
    ShipImage destroyer;
    ShipImage submarine;
    ShipImage patrolBoat;

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

        carrierButton = (Button) findViewById(R.id.selectCarrier);
        carrierButton.setOnClickListener(this);

        battleshipButton = (Button) findViewById(R.id.selectBattleship);
        battleshipButton.setOnClickListener(this);

        destroyerButton = (Button) findViewById(R.id.selectDestroyer);
        destroyerButton.setOnClickListener(this);

        submarineButton = (Button) findViewById(R.id.selectSubmarine);
        submarineButton.setOnClickListener(this);

        ptBoatButton = (Button) findViewById(R.id.selectPTBoat);
        ptBoatButton.setOnClickListener(this);

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
                if (event.getX() > 523) {
                    board.shipsX[0] = 523;
                } else if (event.getX() < 7.05) {
                    board.shipsX[0] = (float) 7.05;
                } else {
                    board.shipsX[0] = event.getX();
                }
                if (event.getY() < 4.93) {
                    board.shipsY[0] = (float) 4.93;
                } else if (event.getY() > 897.13) {
                    board.shipsY[0] = (float) 897.13;
                } else {
                    board.shipsY[0] = event.getY();
                }
            }
            else if (!moveCarrier && moveBattleship && !moveDestroyer && !moveSubmarine && !movePtBoat){
                if (event.getX() > 605.9) {
                    board.shipsX[1] = (float) 605.9;
                } else if (event.getX() < 4.93) {
                    board.shipsX[1] = (float) 4.93;
                } else {
                    board.shipsX[1] = event.getX();
                }
                if (event.getY() < 6.92) {
                    board.shipsY[1] = (float) 6.92;
                } else if (event.getY() > 900.1) {
                    board.shipsY[1] = (float) 900.1;
                } else {
                    board.shipsY[1] = event.getY();
                }
            }
            else if(!moveCarrier && !moveBattleship && moveDestroyer && !moveSubmarine && !movePtBoat)
            {
                if (event.getX() > 692.977) {
                    board.shipsX[2] = (float) 692.977;
                } else if (event.getX() < 4.93) {
                    board.shipsX[2] = (float) 4.93;
                } else {
                    board.shipsX[2] = event.getX();
                }
                if (event.getY() < 6.92) {
                    board.shipsY[2] = (float) 6.92;
                } else if (event.getY() > 901.9) {
                    board.shipsY[2] = (float) 901.9;
                } else {
                    board.shipsY[2] = event.getY();
                }
            }
            else if(!moveCarrier && !moveBattleship && !moveDestroyer && moveSubmarine && !movePtBoat)
            {
                if (event.getX() > 692.977) {
                    board.shipsX[3] = (float) 692.977;
                } else if (event.getX() < 4.93) {
                    board.shipsX[3] = (float) 4.93;
                } else {
                    board.shipsX[3] = event.getX();
                }
                if (event.getY() < 6.92) {
                    board.shipsY[3] = (float) 6.92;
                } else if (event.getY() > 901.9) {
                    board.shipsY[3] = (float) 901.9;
                } else {
                    board.shipsY[3] = event.getY();
                }
            }
            else if(!moveCarrier && !moveBattleship && !moveDestroyer && !moveSubmarine && movePtBoat)
            {
                if (event.getX() > 778.2733) {
                    board.shipsX[4] = (float) 778.27337;
                } else if (event.getX() < 4.93) {
                    board.shipsX[4] = (float) 4.93;
                } else {
                    board.shipsX[4] = event.getX();
                }
                if (event.getY() < 6.92) {
                    board.shipsY[4] = (float) 6.92;
                } else if (event.getY() > 901.9) {
                    board.shipsY[4] = (float) 901.9;
                } else {
                    board.shipsY[4] = event.getY();
                }
            }


            board.getHolder().unlockCanvasAndPost(canvas);
            board.postInvalidate();
        }
        return true;

    }

    public float calculateDifference(int ship1,int ship2)
    {
        float firstShip = board.shipsX[ship1];
        float secondShip = board.shipsY[ship2];

        float difference = firstShip - secondShip;

        return difference;
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
    }
}
