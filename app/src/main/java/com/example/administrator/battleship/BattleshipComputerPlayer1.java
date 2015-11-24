package com.example.administrator.battleship;

import java.util.Random;

/**
 * Created by nathancamacho on 11/20/15.
 */
public class BattleshipComputerPlayer1 {
    private int carrierRow;
    private int carrierCol;
    private boolean carrierHorizontal = true;
    private int battleshipRow;
    private int battleshipCol;
    private boolean battleshipHorizontal = true;
    private BattleshipGameState AIstate = new BattleshipGameState();

    public BattleshipComputerPlayer1(){

    }

    public void generateShipCoordinates(){
        //Carrier
        int dummy = (int) (Math.random());
        if (dummy < 0.5){
            carrierHorizontal = false;
        }

        if (carrierHorizontal) {
            Random ran = new Random();
            carrierCol = ran.nextInt(6);//random number  from 0-5
            carrierRow = (int) (Math.random()*10);//random number from 0-9
            //AIstate.setUpComputerShips(1,carrierRow,carrierCol,true);
        }
        else {
            Random ran2 = new Random();
            carrierRow = ran2.nextInt(6);//random number  from 0-5
            carrierCol = (int) (Math.random()*10);//random number from 0-9
            //AIstate.setUpComputerShips(1,carrierRow,carrierCol,false);
        }

        //Battleship
        dummy = (int) (Math.random());
        if (dummy < 0.5){
            battleshipHorizontal = false;
        }

        if (battleshipHorizontal == true) {
            battleshipRow = (int) (Math.random()*10);//random number from 0-9
            while (battleshipRow == carrierRow){
                battleshipRow = (int) (Math.random()*10);//random number from 0-9
            }
            Random ran3 = new Random();
            battleshipCol = ran3.nextInt(6);//random number  from 0-5
            //AIstate.setUpComputerShips(2,battleshipRow,battleshipCol,true);
        }
        else {
            Random ran2 = new Random();
            carrierRow = ran2.nextInt(6);//random number  from 0-5
            carrierCol = (int) (Math.random()*10);//random number from 0-9
            //AIstate.setUpComputerShips(2,battleshipRow,battleshipCol,false);
        }


    }

    public boolean checkOverlap() {


        return false;
    }
}
