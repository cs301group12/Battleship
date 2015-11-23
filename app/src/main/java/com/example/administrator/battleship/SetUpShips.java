package com.example.administrator.battleship;

import android.graphics.Canvas;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class SetUpShips extends ActionBarActivity implements View.OnTouchListener,View.OnClickListener {

    private SetUpShipsActivity board;
    private LinearLayout top;
    private Button moveUp;
    private Button moveLeft;
    private Button moveDown;
    private Button moveRight;

    private Button carrier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_ships);
        top = (LinearLayout) findViewById(R.id.topView);
        top.setOnTouchListener(this);
        board = (SetUpShipsActivity) findViewById(R.id.board);
        board.setOnTouchListener(this);
        moveLeft = (Button) findViewById(R.id.moveLeft);
        moveLeft.setOnClickListener(this);

        moveRight = (Button) findViewById(R.id.moveRight);
        moveRight.setOnClickListener(this);

        moveUp = (Button) findViewById(R.id.moveUp);
        moveUp.setOnClickListener(this);

        moveDown = (Button) findViewById(R.id.moveDown);
        moveDown.setOnClickListener(this);

        carrier = (Button) findViewById(R.id.carrierButton);
        carrier.setOnClickListener(this);

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
        return false;
    }

    @Override
    public void onClick(View view)
    {
        Canvas canvas = new Canvas();

            if(view == view.findViewById(R.id.moveUp))
            {
                board.shipsY[0] = 50;
                board.shipsX[0] = 200;
                Log.i("Yoo","Move Up");
            }
            else if(view == view.findViewById(R.id.moveDown))
            {
                Log.i("Yoo", "Move Down");
            }
            else if(view == view.findViewById(R.id.moveRight))
            {
                Log.i("Yoo", "Move Right");
            }
            else if(view == moveLeft)
            {
                Log.i("Yoo", "Move Left");
            }
            else
            {
                Log.i("Yoo","Not moving");
            }


    }
}
