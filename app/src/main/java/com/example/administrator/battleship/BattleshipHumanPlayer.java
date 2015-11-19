package com.example.administrator.battleship;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class BattleshipHumanPlayer extends ActionBarActivity implements View.OnTouchListener, View.OnClickListener{

    TextView messageScreen;
    LinearLayout topLayout;
    BoardSV ship1;
    Carrier carrier;
    Canvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing__battleship);

        messageScreen = (TextView) findViewById(R.id.gameInfo);
        topLayout = (LinearLayout) findViewById(R.id.topGUILayout);
        topLayout.setOnTouchListener(this);
        ship1 = (BoardSV) findViewById(R.id.userBoard);
        ship1.setOnTouchListener(this);



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_playing__battleship, menu);
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
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();

        canvas = ship1.getHolder().lockCanvas();
        ship1.carrierX = motionEvent.getX();
        ship1.carrierY = motionEvent.getY();
        ship1.getHolder().unlockCanvasAndPost(canvas);
        ship1.postInvalidate();


        if(x < 950 || x > 1000) {
            messageScreen.setText("Hit!");
            return true;
        }

        messageScreen.setText("reset");

        return false;
    }


    @Override
    public void onClick(View view) {

    }
}
