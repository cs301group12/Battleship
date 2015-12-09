package com.example.administrator.battleship;

/**
 * Created by camachon18 on 12/9/2015.
 */
public class BattleshipComputerPlayer2 {
    private int row;
    private int col;
    private int prevRow;
    private int prevCol;
    private boolean huntMode;
    private boolean killMode;

    public BattleshipComputerPlayer2() {
        huntMode = true;
        killMode = false;
    }

    public void fire(BattleshipGameState state) {
        if (killMode){
            int i = 0;
            while (state.getPlayerID() == 1) {
                if (i == 0) {//fire at right adjacent
                    row = prevRow;
                    col = prevCol + 1;
                    state.shipHit(row, col, 0);
                }
                else if (i == 1) {//fire at left adjacent
                    row = prevRow;
                    col = prevCol - 1;
                    state.shipHit(row, col, 0);
                }
                else if (i == 2) {//fire at top adjacent
                    row = prevRow - 1;
                    col = prevCol;
                    state.shipHit(row, col, 0);
                }
                if (i == 3) {//fire at bottom adjacent
                    row = prevRow + 1;
                    col = prevCol;
                    state.shipHit(row, col, 0);
                }
                i++;
                if (i > 3){
                    break;
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
            prevRow = row;
            prevCol = col;

        }
        else {
            huntMode = true;
            killMode = false;
        }

    }

    public int getRow(){return row;}
    public int getCol(){return col;}

}
