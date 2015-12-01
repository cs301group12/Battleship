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
    Intent intent;


    private BattleshipGameState gameState;
    private BattleshipComputerPlayer1 easyAI;

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
        messageScreen.setText("Your turn first");
        topLayout = (LinearLayout) findViewById(R.id.topGUILayout);
        topLayout.setOnTouchListener(this);
        userBoard = (BoardSV) findViewById(R.id.userBoard);
        userBoard.setOnTouchListener(this);

        intent = getIntent();
        userBoard.shipsX = (float[]) intent.getFloatArrayExtra("Ships X");
        userBoard.shipsY = (float[]) intent.getFloatArrayExtra("Ships Y");
        userBoard.shipOrientations = (boolean[]) intent.getBooleanArrayExtra("Ship Orientations");

        easyAI = new BattleshipComputerPlayer1();

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

    public void computerTurn() {
        int row, col;
        if (gameState.getPlayerID() == 1) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (gameState.getPlayerID() == 1) {
                row = easyAI.generateRandomRow();
                col = easyAI.generateRandomCol();
                gameState.shipHit(row, col, 0);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_playing__battleship, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.quitToMainItem)
        {
            finish();
            return true;
        }
        if (id == R.id.restartGameItem)
        {
            return true;
        }
        if (id == R.id.howToPlayItem)
        {
            startActivity(new Intent(BattleshipHumanPlayer.this,How_to_Play_Screen.class));
            return true;
        }

        return false;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }


    public void checkHitOrMiss(View view) {
        switch (view.getId()) {
            case R.id.A1:
                gameState.shipHit(0,0,1);
                computerTurn();
                break;

            case R.id.A2:
                gameState.shipHit(0,1,1);
                computerTurn();
                break;

            case R.id.A3:
                gameState.shipHit(0,2,1);
                computerTurn();
                break;

            case R.id.A4:
                gameState.shipHit(0,3,1);
                break;

            case R.id.A5:
                gameState.shipHit(0,4,1);
                break;

            case R.id.A6:
                gameState.shipHit(0,5,1);
                break;

            case R.id.A7:
                gameState.shipHit(0,6,1);
                break;

            case R.id.A8:
                gameState.shipHit(0,7,1);
                break;

            case R.id.A9:
                gameState.shipHit(0,8,1);
                break;

            case R.id.A10:
                gameState.shipHit(0,9,1);
                break;

            case R.id.B1:
                gameState.shipHit(1,0,1);
                break;

            case R.id.B2:
                gameState.shipHit(1,1,1);
                break;

            case R.id.B3:
                gameState.shipHit(1,2,1);
                break;

            case R.id.B4:
                gameState.shipHit(1,3,1);
                break;

            case R.id.B5:
                gameState.shipHit(1,4,1);
                break;

            case R.id.B6:
                gameState.shipHit(1,5,1);
                break;

            case R.id.B7:
                gameState.shipHit(1,6,1);
                break;

            case R.id.B8:
                gameState.shipHit(1,7,1);
                break;

            case R.id.B9:
                gameState.shipHit(1,8,1);
                break;

            case R.id.B10:
                gameState.shipHit(1,9,1);
                break;

            case R.id.C1:
                gameState.shipHit(2,0,1);
                break;

            case R.id.C2:
                gameState.shipHit(2,1,1);
                break;

            case R.id.C3:
                gameState.shipHit(2,2,1);
                break;

            case R.id.C4:
                gameState.shipHit(2,3,1);
                break;

            case R.id.C5:
                gameState.shipHit(2,4,1);
                break;

            case R.id.C6:
                gameState.shipHit(2,5,1);
                break;

            case R.id.C7:
                gameState.shipHit(2,6,1);
                break;

            case R.id.C8:
                gameState.shipHit(2,7,1);
                break;

            case R.id.C9:
                gameState.shipHit(2,8,1);
                break;

            case R.id.C10:
                gameState.shipHit(2,9,1);
                break;

            case R.id.D1:
                gameState.shipHit(3,0,1);
                break;

            case R.id.D2:
                gameState.shipHit(3,1,1);
                break;

            case R.id.D3:
                gameState.shipHit(3,2,1);
                break;

            case R.id.D4:
                gameState.shipHit(3,3,1);
                break;

            case R.id.D5:
                gameState.shipHit(3,4,1);
                break;

            case R.id.D6:
                gameState.shipHit(3,5,1);
                break;

            case R.id.D7:
                gameState.shipHit(3,6,1);
                break;

            case R.id.D8:
                gameState.shipHit(3,7,1);
                break;

            case R.id.D9:
                gameState.shipHit(3,8,1);
                break;

            case R.id.D10:
                gameState.shipHit(3,9,1);
                break;

            case R.id.E1:
                gameState.shipHit(4,0,1);
                break;

            case R.id.E2:
                gameState.shipHit(4,1,1);
                break;

            case R.id.E3:
                gameState.shipHit(4,2,1);
                break;

            case R.id.E4:
                gameState.shipHit(4,3,1);
                break;

            case R.id.E5:
                gameState.shipHit(4,4,1);
                break;

            case R.id.E6:
                gameState.shipHit(4,5,1);
                break;

            case R.id.E7:
                gameState.shipHit(4,6,1);
                break;

            case R.id.E8:
                gameState.shipHit(4,7,1);
                break;

            case R.id.E9:
                gameState.shipHit(4,8,1);
                break;

            case R.id.E10:
                gameState.shipHit(4,9,1);
                break;

            case R.id.F1:
                gameState.shipHit(5,0,1);
                break;

            case R.id.F2:
                gameState.shipHit(5,1,1);
                break;

            case R.id.F3:
                gameState.shipHit(5,2,1);
                break;

            case R.id.F4:
                gameState.shipHit(5,3,1);
                break;

            case R.id.F5:
                gameState.shipHit(5,4,1);
                break;

            case R.id.F6:
                gameState.shipHit(5,5,1);
                break;

            case R.id.F7:
                gameState.shipHit(5,6,1);
                break;

            case R.id.F8:
                gameState.shipHit(5,7,1);
                break;

            case R.id.F9:
                gameState.shipHit(5,8,1);
                break;

            case R.id.F10:
                gameState.shipHit(5,9,1);
                break;

            case R.id.G1:
                gameState.shipHit(6,0,1);
                break;

            case R.id.G2:
                gameState.shipHit(6,1,1);
                break;

            case R.id.G3:
                gameState.shipHit(6,2,1);
                break;

            case R.id.G4:
                gameState.shipHit(6,3,1);
                break;

            case R.id.G5:
                gameState.shipHit(6,4,1);
                break;

            case R.id.G6:
                gameState.shipHit(6,5,1);
                break;

            case R.id.G7:
                gameState.shipHit(6,6,1);
                break;

            case R.id.G8:
                gameState.shipHit(6,7,1);
                break;

            case R.id.G9:
                gameState.shipHit(6,8,1);
                break;

            case R.id.G10:
                gameState.shipHit(6,9,1);
                break;

            case R.id.H1:
                gameState.shipHit(7,0,1);
                break;

            case R.id.H2:
                gameState.shipHit(7,1,1);
                break;

            case R.id.H3:
                gameState.shipHit(7,2,1);
                break;

            case R.id.H4:
                gameState.shipHit(7,3,1);
                break;

            case R.id.H5:
                gameState.shipHit(7,4,1);
                break;

            case R.id.H6:
                gameState.shipHit(7,5,1);
                break;

            case R.id.H7:
                gameState.shipHit(7,6,1);
                break;

            case R.id.H8:
                gameState.shipHit(7,7,1);
                break;

            case R.id.H9:
                gameState.shipHit(7,8,1);
                break;

            case R.id.H10:
                gameState.shipHit(7,9,1);
                break;

            case R.id.I1:
                gameState.shipHit(8,0,1);
                break;

            case R.id.I2:
                gameState.shipHit(8,1,1);
                break;

            case R.id.I3:
                gameState.shipHit(8,2,1);
                break;

            case R.id.I4:
                gameState.shipHit(8,3,1);
                break;

            case R.id.I5:
                gameState.shipHit(8,4,1);
                break;

            case R.id.I6:
                gameState.shipHit(8,5,1);
                break;

            case R.id.I7:
                gameState.shipHit(8,6,1);
                break;

            case R.id.I8:
                gameState.shipHit(8,7,1);
                break;

            case R.id.I9:
                gameState.shipHit(8,8,1);
                break;

            case R.id.I10:
                gameState.shipHit(8,9,1);
                break;

            case R.id.J1:
                gameState.shipHit(9,0,1);
                break;

            case R.id.J2:
                gameState.shipHit(9,1,1);
                break;

            case R.id.J3:
                gameState.shipHit(9,2,1);
                break;

            case R.id.J4:
                gameState.shipHit(9,3,1);
                break;

            case R.id.J5:
                gameState.shipHit(9,4,1);
                break;

            case R.id.J6:
                gameState.shipHit(9,5,1);
                break;

            case R.id.J7:
                gameState.shipHit(9,6,1);
                break;

            case R.id.J8:
                gameState.shipHit(9,7,1);
                break;

            case R.id.J9:
                gameState.shipHit(9,8,1);
                break;

            case R.id.J10:
                gameState.shipHit(9,9,1);
                break;


        }

    }
    public String checkIfGameOver (){
        if (gameState.getPlayer1Hits() == 17){
            return "Human won";
        }
        else if (gameState.getPlayer2Hits() == 17 ){
            return "AI won";
        }
        else {
            return null;
        }
    }
    @Override
    public void onClick(View view) {

    }
}
