package com.example.administrator.battleship;

import java.util.Random;

/**
 * @author Nathan Camacho
 * @author Hashim AlJawad
 * @author Kelson Sipe
 *
 * @version  12/1/2015
 *
 * Description of BattleshipComputerPlayer1 class:
 * Currently this class represents our "dumb" AI. This AI chooses random coordinates to fire upon
 *
 */
public class BattleshipComputerPlayer1 {

    public BattleshipComputerPlayer1(){
        //nothing needed
    }

    //returns a random number from 0-9 that will signify the row chosen
    public int generateRandomRow(){
        int row = (int) (Math.random()*10);
        return row;
    }
    //returns a random number from 0-9 that will signify the column chosen
    public int generateRandomCol(){
        int col = (int) (Math.random()*10);
        return col;
    }

}
