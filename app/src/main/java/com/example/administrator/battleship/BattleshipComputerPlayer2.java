package com.example.administrator.battleship;

/**
 * Created by camachon18 on 12/9/2015.
 */
public class BattleshipComputerPlayer2 {
    private int row;
    private int col;
    private int lastHitRow;
    private int lastHitCol;
    private int firstHitRow;
    private int firstHitCol;
    private boolean stillOnTarget;
    private boolean huntMode;
    private boolean killMode;

    public BattleshipComputerPlayer2() {
        huntMode = true;
        killMode = false;
        stillOnTarget = false;
    }

    public void fire(BattleshipGameState state) {
        if (killMode){
            int i = 0;
            while (state.getPlayerID() == 1) {
                if (i == 0) {//fire at right adjacent
                    row = lastHitRow;
                    col = lastHitCol + 1;
                    if (col > 9){//out of bounds on right
                        col = lastHitCol - 1;//change to left of hit
                    }
                    state.shipHit(row, col, 0);
                }
                else if (i == 1) {//fire at left adjacent
                    row = lastHitRow;
                    col = lastHitCol - 1;
                    if (col < 0) {//out of bounds on left
                        row = lastHitRow - 1;//change to fire at top of hit
                        col = lastHitCol;
                    }
                    state.shipHit(row, col, 0);
                }
                else if (i == 2) {//fire at top adjacent
                    row = lastHitRow - 1;
                    col = lastHitCol;
                    if (row < 0) {//out of bounds on top
                        row = lastHitRow + 1;//change to fire below hit
                    }
                    state.shipHit(row, col, 0);
                }
                if (i == 3) {//fire at bottom adjacent
                    row = lastHitRow + 1;
                    col = lastHitCol;
                    if (row > 9) {//out of bounds on bottom
                        break; //no spots left around hit;
                    }
                    state.shipHit(row, col, 0);
                }
                i++;
                if (i > 3){
                    break;
                }
            }
            int j = 0;
            while (state.getPlayerID() == 1) {
                if (j == 0) {//fire at right adjacent
                    row = firstHitRow;
                    col = firstHitCol + 1;
                    if (col > 9){//out of bounds on right
                        col = firstHitCol - 1;//change to left of hit
                    }
                    state.shipHit(row, col, 0);
                }
                else if (j == 1) {//fire at left adjacent
                    row = firstHitRow;
                    col = firstHitCol - 1;
                    if (col < 0) {//out of bounds on left
                        row = firstHitRow - 1;//change to fire at top of hit
                        col = firstHitCol;
                    }
                    state.shipHit(row, col, 0);
                }
                else if (j == 2) {//fire at top adjacent
                    row = firstHitRow - 1;
                    col = firstHitCol;
                    if (row < 0) {//out of bounds on top
                        row = firstHitRow + 1;//change to fire below hit
                    }
                    state.shipHit(row, col, 0);
                }
                if (j == 3) {//fire at bottom adjacent
                    row = firstHitRow + 1;
                    col = firstHitCol;
                    if (row > 9) {//out of bounds on bottom
                        break; //no spots left around hit;
                    }
                    state.shipHit(row, col, 0);
                }
                j++;
                if (j > 3){
                    break;
                }
            }
            if (state.getPlayerID() == 1){//if all spots around hit were checked
                if (firstHitCol != lastHitCol && firstHitRow != lastHitRow) {
                    firstHitRow = lastHitRow;
                    firstHitCol = lastHitCol;
                }
                else {
                    stillOnTarget = false;
                }
                while (state.getPlayerID() == 1) {
                    row = (int) (Math.random() * 10);
                    col = (int) (Math.random() * 10);
                    state.shipHit(row, col, 0);
                }
            }
        }
        else {
            while (state.getPlayerID() == 1) {
                row = (int) (Math.random() * 10);
                col = (int) (Math.random() * 10);
                state.shipHit(row, col, 0);
            }
        }
        if (state.getUserShipHit()){
            huntMode = false;
            killMode = true;
            lastHitRow = row;
            lastHitCol = col;
            if (stillOnTarget == false) {
                stillOnTarget = true;
                firstHitRow = row;
                firstHitCol = col;
            }

        }
        else {
            if (stillOnTarget) {
                huntMode = false;
                killMode = true;
            } else {
                huntMode = true;
                killMode = false;
            }
        }

    }

    public int getRow(){return row;}
    public int getCol(){return col;}

}
