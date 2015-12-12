package com.example.administrator.battleship;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
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
import android.widget.AbsoluteLayout;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * @author Nathan Camacho
 * @author Hashim AlJawad
 * @author Kelson Sipe
 *
 * @version  11/9/2015
 *
 * Description of SetUpShips class:
 * SetUpShips allows the user to set up ships within the bounds set by the game rules.
 * Records the x and y coordinates of the user ships
 *
 **/

public class SetUpShips extends ActionBarActivity implements View.OnTouchListener,View.OnClickListener,View.OnLongClickListener,AdapterView.OnItemSelectedListener{

    //Initializes the layout
    private SetUpShipsActivity board;
    private AbsoluteLayout top;


    private Vibrator v;


    //Initializes the x and y coordinates for each ship
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

    ImageView errorOverlap;

    //Initializes ship buttons
    private Button carrierButton;
    private Button battleshipButton;
    private Button destroyerButton;
    private Button submarineButton;
    private Button ptBoatButton;
    private Button mainMenu;
    private Button saveAndPlay;

    //Initializes the canvas
    private Canvas canvas;

    //Initializes horizontal state to true
    private boolean moveCarrier = true;
    private boolean moveBattleship = true;
    private boolean moveDestroyer = true;
    private boolean moveSubmarine = true;
    private boolean movePtBoat = true;

    //Initializes AI difficulty Spinner
    private Spinner aiDifficulty;

    private ArrayAdapter<String> aiDifficultyAdapterArray;

    private ArrayList<String> aiDifficultyArray;

    private MediaPlayer backgroundMusic5;
    private Button unmute;
    private Button mute;

    /** Creates all of the buttons and sets up listeners for all of the buttons and the set up grid
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        backgroundMusic5 = MediaPlayer.create(this, R.raw.background5);

        //Creates the user board and initializes on touch listeners
        setContentView(R.layout.activity_set_up_ships);
        top = (AbsoluteLayout) findViewById(R.id.topView);
        top.setOnTouchListener(this);
        board = (SetUpShipsActivity) findViewById(R.id.board);
        board.setOnTouchListener(this);
        board.setOnLongClickListener(this);

        //Initializes on click listeners for the main menu and save and play buttons
        mainMenu = (Button) findViewById(R.id.mainMenuButton);
        saveAndPlay = (Button) findViewById(R.id.saveAndPlayButton);
        mainMenu.setOnClickListener(this);
        saveAndPlay.setOnClickListener(this);

        // Initializes listeners for ship buttons. On click will select ships, while on long click
        // will rotate and select ships
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

        errorOverlap = (ImageView) findViewById(R.id.errorOverlapping);

       // String[] aiDifficultyArray = {"normal","hard"};
        aiDifficultyArray = new ArrayList<String>(Arrays.asList("Easy", "Hard"));
        aiDifficulty = (Spinner) findViewById(R.id.aiDiffSpin);
        aiDifficultyAdapterArray = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,aiDifficultyArray);

        aiDifficulty.setAdapter(aiDifficultyAdapterArray);
        aiDifficulty.setOnItemSelectedListener(this);

        System.out.println(aiDifficulty.getSelectedItem().toString() + "\n");

        unmute = (Button) findViewById(R.id.unmuteButtonSetUp);
        mute = (Button) findViewById(R.id.mutebuttonSetUp);
        mute.setOnClickListener(this);
        unmute.setOnClickListener(this);
        playBackgroundMusic();
    }

    public void playBackgroundMusic(){
        backgroundMusic5.start();
        backgroundMusic5.setLooping(true);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l){}

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_set_up_ships, menu);
        return true;
    }

    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Allows the user move ships on the grid. Sets the bounds so that ships cannot be placed
     *  Outside of the grid. Makes the ships snap to each position. Ships will have a specific
     *  x and y coordinate. Gets the values of the x and y coordinate for each ship.
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            canvas = board.getHolder().lockCanvas();
            float testX = event.getX();
            float testY = event.getY();
           //System.out.println("Value of X: " + testX);
           //System.out.println("Value of Y: " + testY);

            //Sets the bounds so that ships cannot be placed Outside of the grid. Makes the ships snap to each position.
            //Ships will have a specific x and y coordinate.
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
                    if (event.getX() < 225) {
                        board.shipsX[1] = (float) 16.83;
                    } else if (event.getX() < 325) {
                        board.shipsX[1] = (float) 112;
                    } else if (event.getX() < 425) {
                        board.shipsX[1] = (float) 211;
                    } else if (event.getX() < 525) {
                        board.shipsX[1] = (float) 305.6;
                    } else if (event.getX() < 625) {
                        board.shipsX[1] = (float) 403;
                    } else if (event.getX() < 725) {
                        board.shipsX[1] = (float) 498;
                    } else if ((event.getX() < 825) || (event.getX() > 825)) {
                        board.shipsX[1] = (float) 594;
                    }
                    if (event.getY() < 98) {
                        board.shipsY[1] = (float) 21.2;
                    } else if (event.getY() < 196) {
                        board.shipsY[1] = (float) 117.39;
                    } else if (event.getY() < 292) {
                        board.shipsY[1] = (float) 212.86;
                    } else if (event.getY() < 388) {
                        board.shipsY[1] = (float) 307.8;
                    } else if (event.getY() < 484) {
                        board.shipsY[1] = (float) 404.8;
                    } else if (event.getY() < 580) {
                        board.shipsY[1] = (float) 501.76;
                    } else if (event.getY() < 676) {
                        board.shipsY[1] = (float) 595.7;
                    } else if (event.getY() < 772) {
                        board.shipsY[1] = (float) 693.2;
                    } else if (event.getY() < 868) {
                        board.shipsY[1] = (float) 788.68;
                    } else if ((event.getY() < 964) || (event.getY() > 964)) {
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


            //Gets the values for the x and y coordinate for each ship.
            carrierRow = getRow((int)board.shipsY[0]);
            carrierCol = getCol((int)board.shipsX[0]);

            battleshipRow = getRow((int)board.shipsY[1]);
            battleshipCol = getCol((int)board.shipsX[1]);

            destroyerRow = getRow((int)board.shipsY[2]);
            destroyerCol = getCol((int)board.shipsX[2]);

            submarineRow = getRow((int)board.shipsY[3]);
            submarineCol = getCol((int)board.shipsX[3]);

            boatRow = getRow((int)board.shipsY[4]);
            boatCol = getCol((int)board.shipsX[4]);


            //calls for the overlap method
            boolean check1 = overlap(0,1, carrierRow, carrierCol, battleshipRow, battleshipCol);

            boolean check2 = overlap(0,2, carrierRow, carrierCol, destroyerRow, destroyerCol);

            boolean check3 = overlap(0,3, carrierRow, carrierCol, submarineRow, submarineCol);

            boolean check4 = overlap(0,4, carrierRow, carrierCol, boatRow, boatCol);

            boolean check5 = overlap(1,0, battleshipRow, battleshipCol, carrierRow, carrierCol);

            boolean check6 = overlap(1,2, battleshipRow, battleshipCol, destroyerRow, destroyerCol);

            boolean check7 = overlap(1,3, battleshipRow, battleshipCol, submarineRow, submarineCol);

            boolean check8 = overlap(1,4, battleshipRow, battleshipCol, boatRow, boatCol);

            boolean check9 = overlap(2,0, destroyerRow, destroyerCol, carrierRow, carrierCol);

            boolean check10 = overlap(2,1, destroyerRow, destroyerCol, battleshipRow, battleshipCol);

            boolean check11 = overlap(2,3, destroyerRow, destroyerCol, submarineRow, submarineCol);

            boolean check12 = overlap(2,4, destroyerRow, destroyerCol, boatRow, boatCol);

            boolean check13 = overlap(3,0, submarineRow, submarineCol, carrierRow, carrierCol);

            boolean check14 = overlap(3,1, submarineRow, submarineCol, battleshipRow, battleshipCol);

            boolean check15 = overlap(3,2, submarineRow, submarineCol, destroyerRow, destroyerCol);

            boolean check16 = overlap(3,4, submarineRow, submarineCol, boatRow, boatCol);

            boolean check17 = overlap(4,0, boatRow, boatCol, carrierRow, carrierCol);

            boolean check18 = overlap(4,1,boatRow,boatCol,battleshipRow,battleshipCol);

            boolean check19 = overlap(4,2,boatRow,boatCol,destroyerRow,destroyerCol);

            boolean check20 = overlap(4,3,boatRow,boatCol,submarineRow,submarineCol);


            //check if at least one of the ships is overlapping and set the save and play button invisivle and display an error.
            if(check1 == true || check2 == true || check3 == true || check4 == true ||
                    check5 == true || check6 == true || check7 == true ||check8 == true
                    ||check9 == true || check10 == true || check11 == true || check12 == true ||
                    check13 == true || check14 == true || check15 == true || check16 == true
                    ||check17 == true || check18 == true || check19 == true || check20 == true)
            {
                saveAndPlay.setVisibility(View.INVISIBLE);
                errorOverlap.setVisibility(View.VISIBLE);
            }
            else
            {
                saveAndPlay.setVisibility(View.VISIBLE);
                errorOverlap.setVisibility(View.INVISIBLE);
            }


            board.getHolder().unlockCanvasAndPost(canvas);
            board.postInvalidate();
        }


        if(event.getAction() == MotionEvent.ACTION_UP)
        {

            /*
            System.out.println("Carrier Row: " + getRow((int) board.shipsY[0]));
            System.out.println("Carrier Col: " + getCol((int)board.shipsX[0]));

            System.out.println("Battleship Row: " + getRow((int)board.shipsY[1]));
            System.out.println("Battleship Col: " + getCol((int) board.shipsX[1]));


            System.out.println("Destroyer Row: " + getRow((int)board.shipsY[2]));
            System.out.println("Destroyer Col: " + getCol((int)board.shipsX[2]));

            System.out.println("Submarine Row: " + getRow((int)board.shipsY[3]));
            System.out.println("Submarine Col: " + getCol((int)board.shipsX[3]));

            System.out.println("Boat Row: " + getRow((int)board.shipsY[4]));
            System.out.println("Boat Col: " + getCol((int)board.shipsX[4]));
            */


        }

        return true;

    }

    //Find the y value of each ship so that it can be passed into the 2D array
    public int getRow(int y)
    {
        //System.out.println("method " + y);
        if(y == 21 || y == 12 || y == 20 || y == 9) { return 0; }
        else if(y == 117 || y == 110 || y == 105) { return 1; }
        else if(y == 212 || y == 210 || y == 205 || y == 201) { return 2; }
        else if(y == 307 || y == 302 || y == 298) { return 3; }
        else if(y == 404 || y == 400 || y == 396 || y == 390) { return 4; }
        else if(y == 501 || y == 500 || y == 495 || y == 490 || y == 498){ return 5; }
        else if(y == 595 || y == 600 || y == 588 || y == 580 || y == 594) { return 6; }
        else if(y == 693 || y == 684 || y == 680 || y == 685) { return 7; }
        else if(y == 788 || y == 772 || y == 776) { return 8; }
        else if(y == 884) { return 9;}
        else { return -1; }
    }

    //Find the x value of each ship so that it can be passed into the 2D array
    public int getCol(int x)
    {
        //System.out.println("method " + x);
        if(x == 0 || x == 16 || x == 22 || x == 10) { return 0; }
        else if(x == 112 || x == 118 || x == 106) { return 1; }
        else if(x == 200 || x == 211 || x == 213 || x == 205) { return 2; }
        else if(x == 305 || x == 310 || x == 298) { return 3; }
        else if(x == 400 || x == 403 || x == 405 || x == 396) { return 4; }
        else if(x == 498 || x == 501 || x == 492){ return 5; }
        else if(x == 589 || x == 594 || x == 598 || x == 588) { return 6; }
        else if(x == 693 || x == 685 || x == 680) { return 7; }
        else if(x == 789 || x == 776) { return 8; }
        else if(x == 885) { return 9;}
        else { return -1; }

    }

    /**
     * Description: A method to check for overlapping ships before playing the game.
     *
     * CAVEAT:
     *
     */
    /** Checks if the carrier is overlapping another ship
     *
     * @param numOfShip
     * @param shipNum
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     * @return
     */
    public boolean overlap(int numOfShip, int shipNum, int row1,int col1,int row2,int col2)
    {
        //variables to determine the ship's current orientation
        boolean battleshipOrientation= board.getBattleshipOrientation() == true;
        boolean submarineOrientation = board.getSubmarineOrientation() == true;
        boolean carrierOrientation = board.getCarrierOrientation() == true;
        boolean boatOrientation =  board.getpTBoatOrientation() == true;
        boolean destroyerOrientation = board.getDestroyerOrientation() == true;

        //if number of the ships is carrier
    if(numOfShip == 0) {
        //check if both ships are on the same row and horizontal
        if ((row1 == row2 && carrierOrientation)) {

            /*
                shipNum = 0 -> Carrier
                shipNum = 1 -> Battleship
                shipNum = 2 -> Destroyer
                shipNum = 3 -> Submarine
                shipNum = 4 -> Patrol Boat
             */

            //if the other ship is battleship, then check if it is horizontal and the distance between the ships is more
            // than or equal -4 and less than or equal to 3 then the ships overlap. Same thing applies for other if statements.
            if (shipNum == 1) { if (col1 - col2 >= -4 && col1 - col2 <= 3 && battleshipOrientation) { return true; }
            //if battleship is vertical and the distance between the ships is more than or equal -4 and less
            //than or equal to 0 then the ships overlap. Same thing applies for other else if statements.
            else if(col1 - col2 >= -4 && col1 - col2 <= 0 && !battleshipOrientation) { return true; } }

            if (shipNum == 2) { if (col1 - col2 >= -4 && col1 - col2 <= 2 && destroyerOrientation) { return true; }
            else if(col1 - col2 >= -4 && col1 - col2 <= 0 && !destroyerOrientation) { return true; } }

            if (shipNum == 3) { if (col1 - col2 >= -4 && col1 - col2 <= 2 && submarineOrientation) { return true; }
            else if(col1 - col2 >= -4 && col1 - col2 <= 0 && !submarineOrientation) { return true; } }

            if (shipNum == 4) { if (col1 - col2 >= -4 && col1 - col2 <= 1 && boatOrientation) { return true; }
            else if(col1 - col2 >= -4 && col1 - col2 <= 0 && !boatOrientation) { return true; } }
        }
        //if carrier is vertical
        else {
            if (!carrierOrientation) {
                //if its battleship then check if its horizontal, then check for the distance between the ships' rows
                // and then check for the distance between the columns.
                //if its vertical, then check if they are on the same column, then check
                // for the distance between the ships' rows. Same applies for the rest of the ships.
                if (shipNum == 1) { if (battleshipOrientation) {
                    if ((row1 - row2 >= -4 && row1 - row2 <= -1) || row1 == row2) {
                        if (col1 - col2 >= 0 && col1 - col2 <= 3) { return true; } } }
                else { if (!battleshipOrientation) {
                        if (col1 == col2) {
                            if ((row1 - row2 >= -4) && (row1 - row2 <= 3)) { return true; } } } } }

                if (shipNum == 2) { if (destroyerOrientation) {
                    if ((row1 - row2 >= -4 && row1 - row2 <= -1) || row1 == row2) {
                        if (col1 - col2 >= 0 && col1 - col2 <= 2) { return true; } } }
                else { if (!destroyerOrientation) {
                        if (col1 == col2) {
                            if ((row1 - row2 >= -4) && (row1 - row2 <= 2)) { return true; } } } } }

                if (shipNum == 3) { if (submarineOrientation) {
                    if ((row1 - row2 >= -4 && row1 - row2 <= -1) || row1 == row2) {
                        if (col1 - col2 >= 0 && col1 - col2 <= 2) { return true; } } }
                else { if (!submarineOrientation) {
                        if (col1 == col2) {
                            if ((row1 - row2 >= -4) && (row1 - row2 <= 2)) { return true; } } } } }

            if (shipNum == 4) { if (boatOrientation) {
                if ((row1 - row2 >= -4 && row1 - row2 <= -1) || row1 == row2) {
                    if (col1 - col2 >= 0 && col1 - col2 <= 1) { return true; } } }
                else { if (!boatOrientation) {
                        if (col1 == col2) {
                            if ((row1 - row2 >= -4) && (row1 - row2 <= 1)) { return true; } } } } }
                }
            }
        }


        if(numOfShip == 1) {
            //battleship
            if ((row1 == row2 && battleshipOrientation)) {

                if (shipNum == 0) { if (col1 - col2 >= -3 && col1 - col2 <= 4 && carrierOrientation) { return true; }
                else if(col1 - col2 >= -3 && col1 - col2 <= 0 && !carrierOrientation) { return true; } }

                if (shipNum == 2) { if (col1 - col2 >= -3 && col1 - col2 <= 2 && destroyerOrientation) { return true; }
                else if(col1 - col2 >= -3 && col1 - col2 <= 0 && !destroyerOrientation) { return true; } }

                if (shipNum == 3) { if (col1 - col2 >= -3 && col1 - col2 <= 2 && submarineOrientation) { return true; }
                else if(col1 - col2 >= -3 && col1 - col2 <= 0 && !submarineOrientation) { return true; } }

                if (shipNum == 4) { if (col1 - col2 >= -3 && col1 - col2 <= 1 && boatOrientation) { return true; }
                else if(col1 - col2 >= -3 && col1 - col2 <= 0 && !boatOrientation) { return true; } }
            }

            else {
                if (!battleshipOrientation) {

                    if (shipNum == 0) { if (carrierOrientation) {
                            if ((row1 - row2 >= -3 && row1 - row2 <= 0) || row1 == row2) {
                                if (col1 - col2 >= 0 && col1 - col2 <= 4) { return true; } } }
                    else { if (!carrierOrientation) {
                                if (col1 == col2) {
                                    if ((row1 - row2 >= -3) && (row1 - row2 <= 4)) { return true; } } } } }

                    if (shipNum == 2) { if (destroyerOrientation) {
                            if ((row1 - row2 >= -3 && row1 - row2 <= 0) || row1 == row2) {
                                if (col1 - col2 >= 0 && col1 - col2 <= 2) { return true; } } }
                    else { if (!destroyerOrientation) {
                                if (col1 == col2) {
                                    if ((row1 - row2 >= -3) && (row1 - row2 <= 2)) { return true; } } } } }

                    if (shipNum == 3) { if (submarineOrientation) {
                            if ((row1 - row2 >= -3 && row1 - row2 <= 0) || row1 == row2) {
                                if (col1 - col2 >= 0 && col1 - col2 <= 2) { return true; } } }
                    else { if (!submarineOrientation) {
                                if (col1 == col2) {
                                    if ((row1 - row2 >= -3) && (row1 - row2 <= 2)) { return true; } } } } }

                    if (shipNum == 4) { if (boatOrientation) {
                            if ((row1 - row2 >= -3 && row1 - row2 <= 0) || row1 == row2) {
                                if (col1 - col2 >= 0 && col1 - col2 <= 1) { return true; } } }
                    else { if (!boatOrientation) {
                                if (col1 == col2) {
                                    if ((row1 - row2 >= -3) && (row1 - row2 <= 1)) { return true; } } } } }
                    }
                }
        }

        //destroyer
        if(numOfShip == 2) {
            if ((row1 == row2 && destroyerOrientation)) {

                if (shipNum == 0) { if (col1 - col2 >= -2 && col1 - col2 <= 4 && carrierOrientation) { return true; }
                else if(col1 - col2 >= -2 && col1 - col2 <= 0 && !carrierOrientation) { return true; } }

                if (shipNum == 1) { if (col1 - col2 >= -2 && col1 - col2 <= 3 && battleshipOrientation) { return true; }
                else if(col1 - col2 >= -2 && col1 - col2 <= 0 && !battleshipOrientation) { return true; } }

                if (shipNum == 3) { if (col1 - col2 >= -2 && col1 - col2 <= 2 && submarineOrientation) { return true; }
                else if(col1 - col2 >= -2 && col1 - col2 <= 0 && !submarineOrientation) { return true; } }

                if (shipNum == 4) { if (col1 - col2 >= -2 && col1 - col2 <= 1 && boatOrientation) { return true; }
                else if(col1 - col2 >= -2 && col1 - col2 <= 0 && !boatOrientation) { return true; } } }

            else {
                if (!destroyerOrientation) {

                    if (shipNum == 0) { if (carrierOrientation) {
                            if ((row1 - row2 >= -2 && row1 - row2 <= 0) || row1 == row2) {
                                if (col1 - col2 >= 0 && col1 - col2 <= 4) { return true; } } }
                    else { if (!carrierOrientation) {
                                if (col1 == col2) {
                                    if ((row1 - row2 >= -2) && (row1 - row2 <= 4)) { return true; } } } } }

                    if (shipNum == 1) { if (battleshipOrientation) {
                            if ((row1 - row2 >= -2 && row1 - row2 <= 0) || row1 == row2) {
                                if (col1 - col2 >= 0 && col1 - col2 <= 3) { return true; } } }
                    else { if (!battleshipOrientation) {
                                if (col1 == col2) {
                                    if ((row1 - row2 >= -2) && (row1 - row2 <= 3)) { return true; } } } } }

                    if (shipNum == 3) { if (submarineOrientation) {
                            if ((row1 - row2 >= -2 && row1 - row2 <= 0) || row1 == row2) {
                                if (col1 - col2 >= 0 && col1 - col2 <= 2) { return true; } } }
                    else { if (!submarineOrientation) {
                                if (col1 == col2) {
                                    if ((row1 - row2 >= -2) && (row1 - row2 <= 2)) { return true; } } } } }

                    if (shipNum == 4) { if (boatOrientation) {
                            if ((row1 - row2 >= -2 && row1 - row2 <= 0) || row1 == row2) {
                                if (col1 - col2 >= 0 && col1 - col2 <= 1) { return true; } } }
                    else { if (!boatOrientation) {
                                if (col1 == col2) {
                                    if ((row1 - row2 >= -2) && (row1 - row2 <= 1)) { return true; } } } } }
                    }
                }
        }

        //submarine
        if(numOfShip == 3) {
            if ((row1 == row2 && submarineOrientation)) {

                if (shipNum == 1) { if (col1 - col2 >= -2 && col1 - col2 <= 3 && battleshipOrientation) { return true; }
                else if(col1 - col2 >= -2 && col1 - col2 <= 0 && !battleshipOrientation) { return true; } }

                if (shipNum == 0) { if (col1 - col2 >= -2 && col1 - col2 <= 4 && carrierOrientation) { return true; }
                else if(col1 - col2 >= -2 && col1 - col2 <= 0 && !carrierOrientation) { return true; } }

                if (shipNum == 2) { if (col1 - col2 >= -2 && col1 - col2 <= 2 && destroyerOrientation) { return true; }
                else if(col1 - col2 >= -2 && col1 - col2 <= 0 && !destroyerOrientation) { return true; } }

                if (shipNum == 4) { if (col1 - col2 >= -2 && col1 - col2 <= 1 && boatOrientation) { return true; }
                else if(col1 - col2 >= -2 && col1 - col2 <= 0 && !boatOrientation) { return true; } } }
            else {
                if (!submarineOrientation) {

                    if (shipNum == 0) { if (carrierOrientation) {
                         if ((row1 - row2 >= -2 && row1 - row2 <= 0) || row1 == row2) {
                             if (col1 - col2 >= 0 && col1 - col2 <= 4) { return true; } } }
                    else { if (!carrierOrientation) {
                         if (col1 == col2) {
                            if ((row1 - row2 >= -2) && (row1 - row2 <= 4)) { return true; } } } } }

                    if (shipNum == 1) { if (battleshipOrientation) {
                         if ((row1 - row2 >= -2 && row1 - row2 <= 0) || row1 == row2) {
                             if (col1 - col2 >= 0 && col1 - col2 <= 3) { return true; } } }
                    else { if (!battleshipOrientation) {
                        if (col1 == col2) {
                            if ((row1 - row2 >= -2) && (row1 - row2 <= 3)) { return true; } } } } }

                   if (shipNum == 2) { if (destroyerOrientation) {
                        if ((row1 - row2 >= -2 && row1 - row2 <= 0) || row1 == row2) {
                             if (col1 - col2 >= 0 && col1 - col2 <= 2) { return true; } } }
                   else { if (!destroyerOrientation) {
                        if (col1 == col2) {
                            if ((row1 - row2 >= -2) && (row1 - row2 <= 2)) { return true; } } } } }

                    if (shipNum == 4) { if (boatOrientation) {
                         if ((row1 - row2 >= -2 && row1 - row2 <= 0) || row1 == row2) {
                             if (col1 - col2 >= 0 && col1 - col2 <= 1) { return true; } } }
                    else { if (!boatOrientation) {
                        if (col1 == col2) {
                            if ((row1 - row2 >= -2) && (row1 - row2 <= 1)) { return true; } } } } }
                }
            }
        }

        //boat
        if(numOfShip == 4) {
            if ((row1 == row2 && boatOrientation)) {

                if (shipNum == 0) { if (col1 - col2 >= -1 && col1 - col2 <= 4 && carrierOrientation) { return true; }
                else if(col1 - col2 >= -1 && col1 - col2 <= 0 && !carrierOrientation) { return true; } }
                if (shipNum == 1) { if (col1 - col2 >= -1 && col1 - col2 <= 3 && battleshipOrientation) { return true; }
                else if(col1 - col2 >= -1 && col1 - col2 <= 0 && !battleshipOrientation) { return true; } }

                if (shipNum == 2) { if (col1 - col2 >= -1 && col1 - col2 <= 2 && destroyerOrientation) { return true; }
                else if(col1 - col2 >= -1 && col1 - col2 <= 0 && !destroyerOrientation) { return true; } }

                if (shipNum == 3) { if (col1 - col2 >= -1 && col1 - col2 <= 2 && destroyerOrientation) { return true; }
                else if(col1 - col2 >= -1 && col1 - col2 <= 0 && !destroyerOrientation) { return true; } } }
            else {
                if (!boatOrientation) {
                    if (shipNum == 0) { if (carrierOrientation) {
                        if ((row1 - row2 >= -1 && row1 - row2 <= 0) || row1 == row2) {
                            if (col1 - col2 >= 0 && col1 - col2 <= 4) { return true; } } }
                    else { if (!carrierOrientation) {
                        if (col1 == col2) {
                            if ((row1 - row2 >= -1) && (row1 - row2 <= 4)) { return true; } } } } }

                    if (shipNum == 1) { if (battleshipOrientation) {
                        if ((row1 - row2 >= -1 && row1 - row2 <= 0) || row1 == row2) {
                            if (col1 - col2 >= 0 && col1 - col2 <= 3) { return true; } } }
                    else { if (!battleshipOrientation) {
                        if (col1 == col2) {
                            if ((row1 - row2 >= -1) && (row1 - row2 <= 3)) { return true; } } } } }

                   if (shipNum == 2) { if (destroyerOrientation) {
                        if ((row1 - row2 >= -1 && row1 - row2 <= 0) || row1 == row2) {
                            if (col1 - col2 >= 0 && col1 - col2 <= 2) { return true; } } }
                   else { if (!destroyerOrientation) {
                        if (col1 == col2) {
                            if ((row1 - row2 >= -1) && (row1 - row2 <= 2)) { return true; } } } } }

                    if (shipNum == 3) { if (submarineOrientation) {
                        if ((row1 - row2 >= -1 && row1 - row2 <= 0) || row1 == row2) {
                            if (col1 - col2 >= 0 && col1 - col2 <= 2) { return true; } } }
                    else { if (!submarineOrientation) {
                        if (col1 == col2) {
                            if ((row1 - row2 >= -1) && (row1 - row2 <= 2)) { return true; } } } } }
                }
            }
        }

      return false; //return false if ships are not overlapping.
    }

    /**When a ship button is clicked, the ship on the grid will be selected. Main menu button will return to the main menu
     * Save and play will save the values of the ships so that they set up correctly in the playing battleship layout; also
     * goes to the playing battleship layout.
     *
     * @param view
     */
    @Override
    public void onClick(View view)
    {
        if(view == carrierButton){
            System.out.println("BATTLESHIP X: " + board.shipsX[1]);
            System.out.println("BATTLESHIP Y: " + board.shipsY[1]);
            moveCarrier = true;
            moveBattleship = false;
            moveDestroyer = false;
            moveSubmarine = false;
            movePtBoat = false;
        }
        if (view == battleshipButton){
            System.out.println("BATTLESHIP X: " + board.shipsX[1]);
            System.out.println("BATTLESHIP Y: " + board.shipsY[1]);
            moveCarrier = false;
            moveBattleship = true;
            moveDestroyer = false;
            moveSubmarine = false;
            movePtBoat = false;
        }
        if (view == destroyerButton) {
            System.out.println("BATTLESHIP X: " + board.shipsX[1]);
            System.out.println("BATTLESHIP Y: " + board.shipsY[1]);
            moveCarrier = false;
            moveBattleship = false;
            moveDestroyer = true;
            moveSubmarine = false;
            movePtBoat = false;
        }
        if (view == submarineButton){
            System.out.println("BATTLESHIP X: " + board.shipsX[1]);
            System.out.println("BATTLESHIP Y: " + board.shipsY[1]);
            moveCarrier = false;
            moveBattleship = false;
            moveDestroyer = false;
            moveSubmarine = true;
            movePtBoat = false;
        }
        if (view == ptBoatButton){
            System.out.println("BATTLESHIP X: " + board.shipsX[1]);
            System.out.println("BATTLESHIP Y: " + board.shipsY[1]);
            moveCarrier = false;
            moveBattleship = false;
            moveDestroyer = false;
            moveSubmarine = false;
            movePtBoat = true;
        }
        if (view == mainMenu){
            startActivity(new Intent(SetUpShips.this, MainActivity.class));
            backgroundMusic5.stop();
            finish();
        }
        if (view == saveAndPlay){
            backgroundMusic5.reset();
            Intent intent = new Intent(this, BattleshipHumanPlayer.class);

            intent.putExtra("Ships X",board.shipsX);
            intent.putExtra("Ships Y",board.shipsY);
            intent.putExtra("Ship Orientations",board.shipOrientations);
            intent.putExtra("difficulty", aiDifficulty.getSelectedItem().toString());

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
        if (view == mute){
            backgroundMusic5.pause();
        }
        if (view == unmute){
            backgroundMusic5.start();
            backgroundMusic5.setLooping(true);
        }
    }

    @Override
    public void onBackPressed() {
        backgroundMusic5.stop();
        startActivity(new Intent(SetUpShips.this, MainActivity.class));
      //  super.onBackPressed();
    }

    /** When a ship button is held down, the ship will be rotated and selected.
     *
     * @param v
     * @return
     */
    @Override
    public boolean onLongClick(View v) {

        if(v == carrierButton) {
            boolean dummy = board.getCarrierOrientation();
            board.setCarrierOrientation(!dummy);
            Toast.makeText(getApplicationContext(),"Carrier Orientation Has Been Changed. Please touch grid now.",Toast.LENGTH_SHORT).show();
            System.out.println("BATTLESHIP X: " + board.shipsX[1]);
            System.out.println("BATTLESHIP Y: " + board.shipsY[1]);
            moveCarrier = true;
            moveBattleship = false;
            moveDestroyer = false;
            moveSubmarine = false;
            movePtBoat = false;
            return true;
        }
        if(v == battleshipButton) {
            boolean dummy2 = board.getBattleshipOrientation();
            board.setBattleshipOrientation(!dummy2);
            Toast.makeText(getApplicationContext(),"Battleship Orientation Has Been Changed. Please touch grid now.",Toast.LENGTH_SHORT).show();
            moveCarrier = false;
            moveBattleship = true;
            moveDestroyer = false;
            moveSubmarine = false;
            movePtBoat = false;
            return true;
        }
        if(v == destroyerButton){
            boolean dummy3 = board.getDestroyerOrientation();
            board.setDestroyerOrientation(!dummy3);
            Toast.makeText(getApplicationContext(),"Destroyer Orientation Has Been Changed. Please touch grid now.",Toast.LENGTH_SHORT).show();
            moveCarrier = false;
            moveBattleship = false;
            moveDestroyer = true;
            moveSubmarine = false;
            movePtBoat = false;
            return true;
        }
        if(v == submarineButton){
            boolean dummy4 = board.getSubmarineOrientation();
            board.setSubmarineOrientation(!dummy4);
            Toast.makeText(getApplicationContext(),"Submarine Orientation Has Been Changed. Please touch grid now.",Toast.LENGTH_SHORT).show();
            moveCarrier = false;
            moveBattleship = false;
            moveDestroyer = false;
            moveSubmarine = true;
            movePtBoat = false;
            return true;
        }
        if(v == ptBoatButton){
            boolean dummy5 = board.getpTBoatOrientation();
            board.setpTBoatOrientation(!dummy5);
            Toast.makeText(getApplicationContext(),"Patrol Boat Orientation Has Been Changed. Please touch grid now.",Toast.LENGTH_SHORT).show();
            moveCarrier = false;
            moveBattleship = false;
            moveDestroyer = false;
            moveSubmarine = false;
            movePtBoat = true;
            return true;
        }
        return false;
    }

}
