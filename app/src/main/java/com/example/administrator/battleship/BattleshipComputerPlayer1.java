package com.example.administrator.battleship;

import java.util.Random;

/**
 * Created by nathancamacho on 11/20/15.
 */
public class BattleshipComputerPlayer1 {

    public BattleshipComputerPlayer1(){

    }

    public int generateRandomRow(){
        int row = (int) (Math.random()*10);
        return row;
    }
    public int generateRandomCol(){
        int col = (int) (Math.random()*10);
        return col;
    }

}
