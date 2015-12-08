package com.example.administrator.battleship;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Nathan Camacho
 * @author Hashim AlJawad
 * @author Kelson Sipe
 *
 * @version  12/1/2015
 *
 * Description of BattleshipGameState class:
 * This class contains all the GUI components for the user to play the game. Alters the game state
 * based on user actions.
 *
 */
public class BattleshipHumanPlayer extends ActionBarActivity implements View.OnClickListener{

    //Instance variables
    //GUI variables
    private TextView messageScreen;
    private LinearLayout topLayout;
    private BoardSV userBoard;
    private Canvas canvas;
    private Button readyToPlay;
    //hit and miss sound variables
    private SoundPool hitSound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    private SoundPool missSound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    private int pickupId1;
    private int pickupId2;

    Intent intent;//to receive data
    private BattleshipGameState gameState;//game state of entire game
    private BattleshipComputerPlayer1 easyAI;//dumb AI
    private boolean AIshipHit;//if an AI's ship has been hit then true
    private boolean userShipHit;//if the user's ship has been hit then true
    private ArrayList<String> aiDifficultyArray;

    private int[] shipVals = new int[10];//array to hold rows and columns where the users ships will be set up


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing__battleship);

        messageScreen = (TextView) findViewById(R.id.gameInfo);
        messageScreen.setText("Your turn first");//initial message
        userBoard = (BoardSV) findViewById(R.id.userBoard);

        //receive info from previous activity
        intent = getIntent();
        userBoard.shipsX = (float[]) intent.getFloatArrayExtra("Ships X");
        userBoard.shipsY = (float[]) intent.getFloatArrayExtra("Ships Y");
        userBoard.shipOrientations = (boolean[]) intent.getBooleanArrayExtra("Ship Orientations");
        shipVals = (int[]) intent.getIntArrayExtra("Ship Set Up");
        aiDifficultyArray = intent.getStringArrayListExtra("player");




        //SHIP'S ROW AND COL VALUES

        //THEY ARE ALL GIVING THE CORRECT VALUES


        System.out.println("carrier ROW " + shipVals[0]);
        System.out.println("carrier COL " + shipVals[1]);

        System.out.println("b ROW " + shipVals[2]);
        System.out.println("b COL " + shipVals[3]);

        System.out.println("d ROW " + shipVals[4]);
        System.out.println("d COL " + shipVals[5]);

        System.out.println("su ROW " + shipVals[6]);
        System.out.println("sub COL " + shipVals[7]);

        System.out.println("boat ROW " + shipVals[8]);
        System.out.println("boat COL " + shipVals[9]);


        //load the sounds to be played
        this.pickupId1 = hitSound.load(this, R.raw.explosion, 1);
        this.pickupId2 = missSound.load(this, R.raw.miss,1);
        easyAI = new BattleshipComputerPlayer1();//create AI

        if(aiDifficultyArray.equals(aiDifficultyArray.get(0))) {
            easyAI = new BattleshipComputerPlayer1();//create AI
        }

        gameState = new BattleshipGameState();//create new game state every time activity is entered
        //array of AI ships to be set up
        Ships[] AIships = new Ships[] {
                new Ships(5),//carrier
                new Ships(4),//battleship
                new Ships(3),//destroyer
                new Ships(3),//sub
                new Ships(2),//pt boat
        };
        gameState.setUpComputerShips(AIships);//set up the AI's ships
        setUpUserShips();//set up the user's ships
        gameState.printBoard();
    }

    /**
     * Description: Method to set AI's ships in the game state.
     *
     * CAVEAT: Currently does not work correctly. Sets the ships up in the same position every time game is played.
     *
     */
    public void setUpUserShips(){
        /*
        gameState.setUpUserShips(1,0,1,true);//carrier row 1 col 2
        gameState.setUpUserShips(2,4,7,false);//battleship row 5 col 8
        gameState.setUpUserShips(3,3,2,true);//destroyer row 4 col 3
        gameState.setUpUserShips(4,9,0,true);//submarine row 10 col 1
        gameState.setUpUserShips(5,6,2,false);//ptBoat row 7 col 3
        */

        gameState.setUpUserShips(1,shipVals[0],shipVals[1],userBoard.shipOrientations[0]);
        gameState.setUpUserShips(2,shipVals[2],shipVals[3],userBoard.shipOrientations[1]);
        gameState.setUpUserShips(3,shipVals[4],shipVals[5],userBoard.shipOrientations[2]);
        gameState.setUpUserShips(4,shipVals[6],shipVals[7],userBoard.shipOrientations[3]);
        gameState.setUpUserShips(5,shipVals[8],shipVals[9],userBoard.shipOrientations[4]);


    }

    /**
     * Description: Method to handle AI's turn.
     *
     * CAVEAT:
     *
     */
    public void computerTurn() {
        if (gameState.getPlayerID() == 1) {//if it's AI's turn
            int row, col;
            while (gameState.getPlayerID() == 1) {//while it is still AI's turn (AI might have chosen a coordinate it previously chose)
                row = easyAI.generateRandomRow();
                col = easyAI.generateRandomCol();
                gameState.shipHit(row, col, 0);//fire on this coordinate
            }
            userShipHit = gameState.getUserShipHit();//if they hit a user's ship tell user
            if (userShipHit) {
                //hitSound.play(this.pickupId1, 1, 1, 1, 0, 1.0f);
                messageScreen.setText("Your ship has been hit!");
            } else {//if they miss a user's ship tell user
                //missSound.play(this.pickupId2, 1, 1, 1, 0, 1.0f);
                messageScreen.setText("Your opponent missed!");
            }
            gameState.setUserShipHit(false);
            checkIfGameOver();//check if AI won
        }
    }

    /**
     * Description: Method to handle user's turn.
     *
     * CAVEAT: Messages don't display on messageScreen
     *
     * @param pressed   the button that was pressed
     */
    public void userTurn(Button pressed) {
        AIshipHit = gameState.getAIShipHit();
        if (AIshipHit) {//if user hit AI's ship
            //messageScreen.setText("You hit a ship!");
            hitSound.play(this.pickupId1, 1, 1, 1, 0, 1.0f);
            pressed.setBackgroundColor(Color.RED);//change button to red to represent hit
        } else {
            missSound.play(this.pickupId2, 1, 1, 1, 0, 1.0f);
            //messageScreen.setText("You missed the enemy ships!");
            pressed.setBackgroundColor(Color.WHITE);//change button to white to represent miss
        }
        //make button unable to be clicked again
        pressed.setEnabled(false);
        pressed.setClickable(false);
        checkIfGameOver();//check if user won
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
            finish();//go back to sey up ships
            return true;
        }
        if (id == R.id.restartGameItem)
        {
            return true;
        }
        if (id == R.id.howToPlayItem)
        {
            startActivity(new Intent(BattleshipHumanPlayer.this,How_to_Play_Screen.class));//go to see how to play
            return true;
        }

        return false;
    }

    /**
     * Description: Method to handle when a user touches a button on the AI's grid
     *
     * CAVEAT: Since there are 100 buttons, there are 100 cases
     *
     * @param view
     */
    public void checkHitOrMiss(View view) {
        switch (view.getId()) {//based on button pressed
            case R.id.A1:
                gameState.shipHit(0, 0, 1);//see if ship was hit at coodrdinate A1
                Button A1 = (Button) findViewById(R.id.A1);
                userTurn(A1);
                computerTurn();
                break;

            case R.id.A2:
                gameState.shipHit(0,1,1);//see if ship was hit at coodrdinate A2 and so on
                Button A2 = (Button) findViewById(R.id.A2);
                userTurn(A2);
                computerTurn();
                break;

            case R.id.A3:
                gameState.shipHit(0,2,1);
                Button A3 = (Button) findViewById(R.id.A3);
                userTurn(A3);
                computerTurn();
                break;

            case R.id.A4:
                gameState.shipHit(0,3,1);
                Button A4 = (Button) findViewById(R.id.A4);
                userTurn(A4);
                computerTurn();
                break;

            case R.id.A5:
                gameState.shipHit(0,4,1);
                Button A5 = (Button) findViewById(R.id.A5);
                userTurn(A5);
                computerTurn();
                break;

            case R.id.A6:
                gameState.shipHit(0,5,1);
                Button A6 = (Button) findViewById(R.id.A6);
                userTurn(A6);
                computerTurn();
                break;

            case R.id.A7:
                gameState.shipHit(0,6,1);
                Button A7 = (Button) findViewById(R.id.A7);
                userTurn(A7);
                computerTurn();
                break;

            case R.id.A8:
                gameState.shipHit(0,7,1);
                Button A8 = (Button) findViewById(R.id.A8);
                userTurn(A8);
                computerTurn();
                break;

            case R.id.A9:
                gameState.shipHit(0,8,1);
                Button A9 = (Button) findViewById(R.id.A9);
                userTurn(A9);
                computerTurn();
                break;

            case R.id.A10:
                gameState.shipHit(0,9,1);
                Button A10 = (Button) findViewById(R.id.A10);
                userTurn(A10);
                computerTurn();
                break;

            case R.id.B1:
                gameState.shipHit(1,0,1);
                Button B1 = (Button) findViewById(R.id.B1);
                userTurn(B1);
                computerTurn();
                break;

            case R.id.B2:
                gameState.shipHit(1,1,1);
                Button B2 = (Button) findViewById(R.id.B2);
                userTurn(B2);
                computerTurn();
                break;

            case R.id.B3:
                gameState.shipHit(1,2,1);
                Button B3 = (Button) findViewById(R.id.B3);
                userTurn(B3);
                computerTurn();
                break;

            case R.id.B4:
                gameState.shipHit(1,3,1);
                Button B4 = (Button) findViewById(R.id.B4);
                userTurn(B4);
                computerTurn();
                break;

            case R.id.B5:
                gameState.shipHit(1,4,1);
                Button B5 = (Button) findViewById(R.id.B5);
                userTurn(B5);
                computerTurn();
                break;

            case R.id.B6:
                gameState.shipHit(1,5,1);
                Button B6 = (Button) findViewById(R.id.B6);
                userTurn(B6);
                computerTurn();
                break;

            case R.id.B7:
                gameState.shipHit(1,6,1);
                Button B7 = (Button) findViewById(R.id.B7);
                userTurn(B7);
                computerTurn();
                break;

            case R.id.B8:
                gameState.shipHit(1,7,1);
                Button B8 = (Button) findViewById(R.id.B8);
                userTurn(B8);
                computerTurn();
                break;

            case R.id.B9:
                gameState.shipHit(1,8,1);
                Button B9 = (Button) findViewById(R.id.B9);
                userTurn(B9);
                computerTurn();
                break;

            case R.id.B10:
                gameState.shipHit(1,9,1);
                Button B10 = (Button) findViewById(R.id.B10);
                userTurn(B10);
                computerTurn();
                break;

            case R.id.C1:
                gameState.shipHit(2,0,1);
                Button C1 = (Button) findViewById(R.id.C1);
                userTurn(C1);
                computerTurn();
                break;

            case R.id.C2:
                gameState.shipHit(2,1,1);
                Button C2 = (Button) findViewById(R.id.C2);
                userTurn(C2);
                computerTurn();
                break;

            case R.id.C3:
                gameState.shipHit(2,2,1);
                Button C3 = (Button) findViewById(R.id.C3);
                userTurn(C3);
                computerTurn();
                break;

            case R.id.C4:
                gameState.shipHit(2,3,1);
                Button C4 = (Button) findViewById(R.id.C4);
                userTurn(C4);
                computerTurn();
                break;

            case R.id.C5:
                gameState.shipHit(2,4,1);
                Button C5 = (Button) findViewById(R.id.C5);
                userTurn(C5);
                computerTurn();
                break;

            case R.id.C6:
                gameState.shipHit(2,5,1);
                Button C6 = (Button) findViewById(R.id.C6);
                userTurn(C6);
                computerTurn();
                break;

            case R.id.C7:
                gameState.shipHit(2,6,1);
                Button C7 = (Button) findViewById(R.id.C7);
                userTurn(C7);
                computerTurn();
                break;

            case R.id.C8:
                gameState.shipHit(2,7,1);
                Button C8 = (Button) findViewById(R.id.C8);
                userTurn(C8);
                computerTurn();
                break;

            case R.id.C9:
                gameState.shipHit(2,8,1);
                Button C9 = (Button) findViewById(R.id.C9);
                userTurn(C9);
                computerTurn();
                break;

            case R.id.C10:
                gameState.shipHit(2,9,1);
                Button C10 = (Button) findViewById(R.id.C10);
                userTurn(C10);
                computerTurn();
                break;

            case R.id.D1:
                gameState.shipHit(3,0,1);
                Button D1 = (Button) findViewById(R.id.D1);
                userTurn(D1);
                computerTurn();
                break;

            case R.id.D2:
                gameState.shipHit(3,1,1);
                Button D2 = (Button) findViewById(R.id.D2);
                userTurn(D2);
                computerTurn();
                break;

            case R.id.D3:
                gameState.shipHit(3,2,1);
                Button D3 = (Button) findViewById(R.id.D3);
                userTurn(D3);
                computerTurn();
                break;

            case R.id.D4:
                gameState.shipHit(3,3,1);
                Button D4 = (Button) findViewById(R.id.D4);
                userTurn(D4);
                computerTurn();
                break;

            case R.id.D5:
                gameState.shipHit(3,4,1);
                Button D5 = (Button) findViewById(R.id.D5);
                userTurn(D5);
                computerTurn();
                break;

            case R.id.D6:
                gameState.shipHit(3,5,1);
                Button D6 = (Button) findViewById(R.id.D6);
                userTurn(D6);
                computerTurn();
                break;

            case R.id.D7:
                gameState.shipHit(3,6,1);
                Button D7 = (Button) findViewById(R.id.D7);
                userTurn(D7);
                computerTurn();
                break;

            case R.id.D8:
                gameState.shipHit(3,7,1);
                Button D8 = (Button) findViewById(R.id.D8);
                userTurn(D8);
                computerTurn();
                break;

            case R.id.D9:
                gameState.shipHit(3,8,1);
                Button D9 = (Button) findViewById(R.id.D9);
                userTurn(D9);
                computerTurn();
                break;

            case R.id.D10:
                gameState.shipHit(3,9,1);
                Button D10 = (Button) findViewById(R.id.D10);
                userTurn(D10);
                computerTurn();
                break;

            case R.id.E1:
                gameState.shipHit(4,0,1);
                Button E1 = (Button) findViewById(R.id.E1);
                userTurn(E1);
                computerTurn();
                break;

            case R.id.E2:
                gameState.shipHit(4,1,1);
                Button E2 = (Button) findViewById(R.id.E2);
                userTurn(E2);
                computerTurn();
                break;

            case R.id.E3:
                gameState.shipHit(4,2,1);
                Button E3 = (Button) findViewById(R.id.E3);
                userTurn(E3);
                computerTurn();
                break;

            case R.id.E4:
                gameState.shipHit(4,3,1);
                Button E4 = (Button) findViewById(R.id.E4);
                userTurn(E4);
                computerTurn();
                break;

            case R.id.E5:
                gameState.shipHit(4,4,1);
                Button E5 = (Button) findViewById(R.id.E5);
                userTurn(E5);
                computerTurn();
                break;

            case R.id.E6:
                gameState.shipHit(4,5,1);
                Button E6 = (Button) findViewById(R.id.E6);
                userTurn(E6);
                computerTurn();
                break;

            case R.id.E7:
                gameState.shipHit(4,6,1);
                Button E7 = (Button) findViewById(R.id.E7);
                userTurn(E7);
                computerTurn();
                break;

            case R.id.E8:
                gameState.shipHit(4,7,1);
                Button E8 = (Button) findViewById(R.id.E8);
                userTurn(E8);
                computerTurn();
                break;

            case R.id.E9:
                gameState.shipHit(4,8,1);
                Button E9 = (Button) findViewById(R.id.E9);
                userTurn(E9);
                computerTurn();
                break;

            case R.id.E10:
                gameState.shipHit(4,9,1);
                Button E10 = (Button) findViewById(R.id.E10);
                userTurn(E10);
                computerTurn();
                break;

            case R.id.F1:
                gameState.shipHit(5,0,1);
                Button F1 = (Button) findViewById(R.id.F1);
                userTurn(F1);
                computerTurn();
                break;

            case R.id.F2:
                gameState.shipHit(5,1,1);
                Button F2 = (Button) findViewById(R.id.F2);
                userTurn(F2);
                computerTurn();
                break;

            case R.id.F3:
                gameState.shipHit(5,2,1);
                Button F3 = (Button) findViewById(R.id.F3);
                userTurn(F3);
                computerTurn();
                break;

            case R.id.F4:
                gameState.shipHit(5,3,1);
                Button F4 = (Button) findViewById(R.id.F4);
                userTurn(F4);
                computerTurn();
                break;

            case R.id.F5:
                gameState.shipHit(5,4,1);
                Button F5 = (Button) findViewById(R.id.F5);
                userTurn(F5);
                computerTurn();
                break;

            case R.id.F6:
                gameState.shipHit(5,5,1);
                Button F6 = (Button) findViewById(R.id.F6);
                userTurn(F6);
                computerTurn();
                break;

            case R.id.F7:
                gameState.shipHit(5,6,1);
                Button F7 = (Button) findViewById(R.id.F7);
                userTurn(F7);
                computerTurn();
                break;

            case R.id.F8:
                gameState.shipHit(5,7,1);
                Button F8 = (Button) findViewById(R.id.F8);
                userTurn(F8);
                computerTurn();
                break;

            case R.id.F9:
                gameState.shipHit(5,8,1);
                Button F9 = (Button) findViewById(R.id.F9);
                userTurn(F9);
                computerTurn();
                break;

            case R.id.F10:
                gameState.shipHit(5,9,1);
                Button F10 = (Button) findViewById(R.id.F10);
                userTurn(F10);
                computerTurn();
                break;

            case R.id.G1:
                gameState.shipHit(6,0,1);
                Button G1 = (Button) findViewById(R.id.G1);
                userTurn(G1);
                computerTurn();
                break;

            case R.id.G2:
                gameState.shipHit(6,1,1);
                Button G2 = (Button) findViewById(R.id.G2);
                userTurn(G2);
                computerTurn();
                break;

            case R.id.G3:
                gameState.shipHit(6,2,1);
                Button G3 = (Button) findViewById(R.id.G3);
                userTurn(G3);
                computerTurn();
                break;

            case R.id.G4:
                gameState.shipHit(6,3,1);
                Button G4 = (Button) findViewById(R.id.G4);
                userTurn(G4);
                computerTurn();
                break;

            case R.id.G5:
                gameState.shipHit(6,4,1);
                Button G5 = (Button) findViewById(R.id.G5);
                userTurn(G5);
                computerTurn();
                break;

            case R.id.G6:
                gameState.shipHit(6,5,1);
                Button G6 = (Button) findViewById(R.id.G6);
                userTurn(G6);
                computerTurn();
                break;

            case R.id.G7:
                gameState.shipHit(6,6,1);
                Button G7 = (Button) findViewById(R.id.G7);
                userTurn(G7);
                computerTurn();
                break;

            case R.id.G8:
                gameState.shipHit(6,7,1);
                Button G8 = (Button) findViewById(R.id.G8);
                userTurn(G8);
                computerTurn();
                break;

            case R.id.G9:
                gameState.shipHit(6,8,1);
                Button G9 = (Button) findViewById(R.id.G9);
                userTurn(G9);
                computerTurn();
                break;

            case R.id.G10:
                gameState.shipHit(6,9,1);
                Button G10 = (Button) findViewById(R.id.G10);
                userTurn(G10);
                computerTurn();
                break;

            case R.id.H1:
                gameState.shipHit(7,0,1);
                Button H1 = (Button) findViewById(R.id.H1);
                userTurn(H1);
                computerTurn();
                break;

            case R.id.H2:
                gameState.shipHit(7,1,1);
                Button H2 = (Button) findViewById(R.id.H2);
                userTurn(H2);
                computerTurn();
                break;

            case R.id.H3:
                gameState.shipHit(7,2,1);
                Button H3 = (Button) findViewById(R.id.H3);
                userTurn(H3);
                computerTurn();
                break;

            case R.id.H4:
                gameState.shipHit(7,3,1);
                Button H4 = (Button) findViewById(R.id.H4);
                userTurn(H4);
                computerTurn();
                break;

            case R.id.H5:
                gameState.shipHit(7,4,1);
                Button H5 = (Button) findViewById(R.id.H5);
                userTurn(H5);
                computerTurn();
                break;

            case R.id.H6:
                gameState.shipHit(7,5,1);
                Button H6 = (Button) findViewById(R.id.H6);
                userTurn(H6);
                computerTurn();
                break;

            case R.id.H7:
                gameState.shipHit(7,6,1);
                Button H7 = (Button) findViewById(R.id.H7);
                userTurn(H7);
                computerTurn();
                break;

            case R.id.H8:
                gameState.shipHit(7,7,1);
                Button H8 = (Button) findViewById(R.id.H8);
                userTurn(H8);
                computerTurn();
                break;

            case R.id.H9:
                gameState.shipHit(7,8,1);
                Button H9 = (Button) findViewById(R.id.H9);
                userTurn(H9);
                computerTurn();
                break;

            case R.id.H10:
                gameState.shipHit(7,9,1);
                Button H10 = (Button) findViewById(R.id.H10);
                userTurn(H10);
                computerTurn();
                break;

            case R.id.I1:
                gameState.shipHit(8,0,1);
                Button I1 = (Button) findViewById(R.id.I1);
                userTurn(I1);
                computerTurn();
                break;

            case R.id.I2:
                gameState.shipHit(8,1,1);
                Button I2 = (Button) findViewById(R.id.I2);
                userTurn(I2);
                computerTurn();
                break;

            case R.id.I3:
                gameState.shipHit(8,2,1);
                Button I3 = (Button) findViewById(R.id.I3);
                userTurn(I3);
                computerTurn();
                break;

            case R.id.I4:
                gameState.shipHit(8,3,1);
                Button I4 = (Button) findViewById(R.id.I4);
                userTurn(I4);
                computerTurn();
                break;

            case R.id.I5:
                gameState.shipHit(8,4,1);
                Button I5 = (Button) findViewById(R.id.I5);
                userTurn(I5);
                computerTurn();
                break;

            case R.id.I6:
                gameState.shipHit(8,5,1);
                Button I6 = (Button) findViewById(R.id.I6);
                userTurn(I6);
                computerTurn();
                break;

            case R.id.I7:
                gameState.shipHit(8,6,1);
                Button I7 = (Button) findViewById(R.id.I7);
                userTurn(I7);
                computerTurn();
                break;

            case R.id.I8:
                gameState.shipHit(8,7,1);
                Button I8 = (Button) findViewById(R.id.I8);
                userTurn(I8);
                computerTurn();
                break;

            case R.id.I9:
                gameState.shipHit(8,8,1);
                Button I9 = (Button) findViewById(R.id.I9);
                userTurn(I9);
                computerTurn();
                break;

            case R.id.I10:
                gameState.shipHit(8,9,1);
                Button I10 = (Button) findViewById(R.id.I10);
                userTurn(I10);
                computerTurn();
                break;

            case R.id.J1:
                gameState.shipHit(9,0,1);
                Button J1 = (Button) findViewById(R.id.J1);
                userTurn(J1);
                computerTurn();
                break;

            case R.id.J2:
                gameState.shipHit(9,1,1);
                Button J2 = (Button) findViewById(R.id.J2);
                userTurn(J2);
                computerTurn();
                break;

            case R.id.J3:
                gameState.shipHit(9,2,1);
                Button J3 = (Button) findViewById(R.id.J3);
                userTurn(J3);
                computerTurn();
                break;

            case R.id.J4:
                gameState.shipHit(9,3,1);
                Button J4 = (Button) findViewById(R.id.J4);
                userTurn(J4);
                computerTurn();
                break;

            case R.id.J5:
                gameState.shipHit(9,4,1);
                Button J5 = (Button) findViewById(R.id.J5);
                userTurn(J5);
                computerTurn();
                break;

            case R.id.J6:
                gameState.shipHit(9,5,1);
                Button J6 = (Button) findViewById(R.id.J6);
                userTurn(J6);
                computerTurn();
                break;

            case R.id.J7:
                gameState.shipHit(9,6,1);
                Button J7 = (Button) findViewById(R.id.J7);
                userTurn(J7);
                computerTurn();
                break;

            case R.id.J8:
                gameState.shipHit(9,7,1);
                Button J8 = (Button) findViewById(R.id.J8);
                userTurn(J8);
                computerTurn();
                break;

            case R.id.J9:
                gameState.shipHit(9,8,1);
                Button J9 = (Button) findViewById(R.id.J9);
                userTurn(J9);
                computerTurn();
                break;

            case R.id.J10:
                gameState.shipHit(9,9,1);
                Button J10 = (Button) findViewById(R.id.J10);
                userTurn(J10);
                computerTurn();
                break;


        }

    }

    /**
     * Description: Method to check if someone has won the game.
     *
     * CAVEAT:
     */
    public void checkIfGameOver (){
        if (gameState.getPlayer1Hits() == 17) {//if user won
            messageScreen.setText("Victory! You sunk all the enemy ships!");
            //code to wait 4 seconds then exit activity
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    finish();
                }
            }, 4000);
        } else if (gameState.getPlayer2Hits() == 17) {//if AI won
            messageScreen.setText("No! All your ships have been sunk! Game Over");
            //code to wait 4 seconds then exit activity
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    finish();
                }
            }, 4000);
        }

    }
    @Override
    public void onClick(View view) {

    }
}
