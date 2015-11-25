package com.example.administrator.battleship;

import android.content.Context;
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


public class SetUpShips extends ActionBarActivity implements View.OnTouchListener,View.OnClickListener {

    private SetUpShipsActivity board;
    private LinearLayout top;
    ImageView carrierImageHor;
    ImageView battleshipImageHor;
    ImageView destroyerImageHor;
    ImageView submarineImageHor;
    ImageView patrolBoatImageHor;

    ImageView carrierImageVer;
    ImageView battleshipImageVer;
    ImageView destroyerImageVer;
    ImageView submarineImageVer;
    ImageView patrolBoatImageVer;

    ShipImage carrier;
    ShipImage battleship;
    ShipImage destroyer;
    ShipImage submarine;
    ShipImage patrolBoat;

    private Button moveUp;
    private Button moveLeft;
    private Button moveDown;
    private Button moveRight;
    private Button goToPlayGame;


    private Canvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_ships);
        top = (LinearLayout) findViewById(R.id.topView);
        top.setOnTouchListener(this);
        board = (SetUpShipsActivity) findViewById(R.id.board);
        board.setOnTouchListener(this);
        /*
        moveLeft = (Button) findViewById(R.id.moveLeft);
        moveLeft.setOnClickListener(this);

        moveRight = (Button) findViewById(R.id.moveRight);
        moveRight.setOnClickListener(this);

        moveUp = (Button) findViewById(R.id.moveUp);
        moveUp.setOnClickListener(this);

        moveDown = (Button) findViewById(R.id.moveDown);
        moveDown.setOnClickListener(this);
        */



        carrierImageHor = (ImageView) findViewById(R.id.carrierHor);
        battleshipImageHor = (ImageView) findViewById(R.id.battleshipHor);
        destroyerImageHor = (ImageView) findViewById(R.id.destroyerHor);
        submarineImageHor = (ImageView) findViewById(R.id.submarineHor);
        patrolBoatImageHor = (ImageView) findViewById(R.id.ptBoatHor);


        carrierImageVer = (ImageView) findViewById(R.id.carrierVer);
        battleshipImageVer = (ImageView) findViewById(R.id.battleshipVer);
        destroyerImageVer = (ImageView) findViewById(R.id.destroyerVer);
        submarineImageVer = (ImageView) findViewById(R.id.submarineVer);
        patrolBoatImageVer = (ImageView) findViewById(R.id.ptBoatVer);

        carrierImageHor.setOnClickListener(this);
        battleshipImageHor.setOnClickListener(this);
        destroyerImageHor.setOnClickListener(this);
        submarineImageHor.setOnClickListener(this);
        patrolBoatImageHor.setOnClickListener(this);

        carrierImageVer.setOnClickListener(this);
        battleshipImageVer.setOnClickListener(this);
        destroyerImageVer.setOnClickListener(this);
        submarineImageVer.setOnClickListener(this);
        patrolBoatImageVer.setOnClickListener(this);

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
            board.shipsX[1] = MotionEventCompat.getX();
            board.shipsY[1] = event.getY();
            board.getHolder().unlockCanvasAndPost(canvas);
            board.postInvalidate();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view)
    {
        if(view == battleshipImageHor)
        {


            Log.i("Clicked", "Battleship image clicked");
            //System.out.println(x);
            //System.out.println(y);
        }

    }
}
