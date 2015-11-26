package com.example.administrator.battleship;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
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




        //goToPlayGame = (Button) findViewById(R.id.)

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
            if ( moveBattleship && !moveCarrier) {
                if (event.getX() > 700) {
                    board.shipsX[1] = 700;
                } else if (event.getX() < 1.7) {
                    board.shipsX[1] = (float) 1.7;
                } else {
                    board.shipsX[1] = event.getX();
                }
                if (event.getY() < -10.5) {
                    board.shipsY[1] = (float) -10.5;
                } else if (event.getY() > 889.5) {
                    board.shipsY[1] = (float) 889.5;
                } else {
                    board.shipsY[1] = event.getY();
                }
            }
            else {
                if (event.getX() > 700) {
                    board.shipsX[0] = 700;
                } else if (event.getX() < 1.7) {
                    board.shipsX[0] = (float) 1.7;
                } else {
                    board.shipsX[0] = event.getX();
                }
                if (event.getY() < -10.5) {
                    board.shipsY[0] = (float) -10.5;
                } else if (event.getY() > 889.5) {
                    board.shipsY[0] = (float) 889.5;
                } else {
                    board.shipsY[0] = event.getY();
                }
            }


            board.getHolder().unlockCanvasAndPost(canvas);
            board.postInvalidate();
        }
        return true;

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
