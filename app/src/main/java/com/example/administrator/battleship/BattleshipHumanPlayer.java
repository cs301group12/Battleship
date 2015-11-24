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

    private TextView messageScreen;
    private LinearLayout topLayout;
    private BoardSV userBoard;
    private Canvas canvas;
    private Button readyToPlay;

    private BattleshipGameState gameState;

    private boolean moveCarrier = true;
    private boolean moveBattleship = true;
    private boolean moveDestroyer = true;
    private boolean moveSubmarine = true;
    private boolean movePTBoat = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing__battleship);

        messageScreen = (TextView) findViewById(R.id.gameInfo);
        topLayout = (LinearLayout) findViewById(R.id.topGUILayout);
        topLayout.setOnTouchListener(this);
        userBoard = (BoardSV) findViewById(R.id.userBoard);
        userBoard.setOnTouchListener(this);

        readyToPlay = (Button) findViewById(R.id.readyToPlay);
        readyToPlay.setOnClickListener(this);

        gameState = new BattleshipGameState();
        Ships[] AIships = new Ships[] {
                new Ships(5),
                new Ships(4),
                new Ships(3),
                new Ships(3),
                new Ships(2),
        };
        //battleshipBoard.place(ships);

        gameState.setUpComputerShips(AIships);
        gameState.printBoard();

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

        if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            if (moveCarrier && !moveBattleship && !moveDestroyer && !movePTBoat && !moveSubmarine) {
                canvas = userBoard.getHolder().lockCanvas();
                userBoard.shipsX[0] = motionEvent.getX();
                userBoard.shipsY[0] = motionEvent.getY();
                userBoard.getHolder().unlockCanvasAndPost(canvas);
                userBoard.postInvalidate();
            }
            if (moveBattleship && !moveCarrier && !moveDestroyer && !movePTBoat && !moveSubmarine) {
                canvas = userBoard.getHolder().lockCanvas();
                userBoard.shipsX[1] = motionEvent.getX();
                userBoard.shipsY[1] = motionEvent.getY();
                userBoard.getHolder().unlockCanvasAndPost(canvas);
                userBoard.postInvalidate();
            }
            if (moveDestroyer && !moveBattleship && !moveCarrier && !movePTBoat && !moveSubmarine) {
                canvas = userBoard.getHolder().lockCanvas();
                userBoard.shipsX[2] = motionEvent.getX();
                userBoard.shipsY[2] = motionEvent.getY();
                userBoard.getHolder().unlockCanvasAndPost(canvas);
                userBoard.postInvalidate();
            }
            if (moveSubmarine && !moveBattleship && !moveDestroyer && !movePTBoat && !moveCarrier) {
                canvas = userBoard.getHolder().lockCanvas();
                userBoard.shipsX[3] = motionEvent.getX();
                userBoard.shipsY[3] = motionEvent.getY();
                userBoard.getHolder().unlockCanvasAndPost(canvas);
                userBoard.postInvalidate();
            }
            if (movePTBoat && !moveBattleship && !moveDestroyer && !moveCarrier && !moveSubmarine) {
                canvas = userBoard.getHolder().lockCanvas();
                userBoard.shipsX[4] = motionEvent.getX();
                userBoard.shipsY[4] = motionEvent.getY();
                userBoard.getHolder().unlockCanvasAndPost(canvas);
                userBoard.postInvalidate();
            }
        }
        if(x < 950 || x > 1000) {
            messageScreen.setText("Hit!");
            return true;
        }

        messageScreen.setText("reset");

        return true;
    }


    @Override
    public void onClick(View view) {

        if (view == readyToPlay) {

        }
    }
}
