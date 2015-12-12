package com.example.administrator.battleship;

/**
 * @author Nathan Camacho
 * @author Hashim AlJawad
 * @author Kelson Sipe
 *
 * @version  12/9/2015
 *
 * Description of BattleshipComputerPlayer2 class:
 * Currently this class represents our "hard" AI. This AI chooses random coordinates, but then chooses locations
 * around a ship they hit. May be seen as not "hard" enough
 *
 */
public class BattleshipComputerPlayer2 {
    //Instance variables
    //row and col chosen by AI
    private int row;
    private int col;
    //variables to keep track of the AI's last hit/first hit
    private int lastHitRow;
    private int lastHitCol;
    private int firstHitRow;
    private int firstHitCol;
    private boolean stillOnTarget;//we are still looking for locations around a hit
    private boolean goVertical;//tells the AI to choose vertical locations around hit
    private boolean goHorizontal;//tells AI to choose horizontal locations around  hit
    private boolean huntMode;//AI is "hunting"(searching) for ships
    private boolean killMode;//AI has hit a ship so now it switches to a mode where it's trying to sink the ship
    private int[][] userGrid = new int[10][10];//replica of user grid to be used by AI to see if they made
    //two vertical hits in a row and to "cheat" a little (for the hard AI)
    private int userShipRow;//Variables to store location of user ship. Used for hard AI to cheat
    private int userShipCol;
    private boolean userShipFound;

    //Initialize variables in consrtuctor
    public BattleshipComputerPlayer2() {
        huntMode = true;
        killMode = false;
        stillOnTarget = false;
        goVertical = false;
        goHorizontal = false;
    }

    /**
     * Description: Method that allows the AI to choose appropriate locations to fire
     *
     * CAVEAT:
     *
     * @param state The game state variable passed in from BattleshipHumanPlayer
     */
    public void fire(BattleshipGameState state, int whichAI) {
        if (killMode){//If in kill mode, then look for locations around hit
            if (!goVertical) {//The user's ship is probably horizontal
                int i = 0;
                while (state.getPlayerID() == 1) {//while it's still the AI's turn
                    if (i == 0) {//fire at right adjacent
                        row = lastHitRow;
                        col = lastHitCol + 1;
                        if (col > 9) {//out of bounds on right
                            col = lastHitCol - 1;//change to left of hit
                        }
                        state.shipHit(row, col, 0);
                    } else if (i == 1) {//fire at left adjacent
                        row = lastHitRow;
                        col = lastHitCol - 1;
                        if (col < 0) {//out of bounds on left
                            row = lastHitRow - 1;//change to fire at top of hit
                            col = lastHitCol;
                            if (row < 0){//out of bounds on top
                                row = lastHitRow + 1;//change to bottom
                            }
                        }
                        state.shipHit(row, col, 0);
                    }
                    if (!goHorizontal) {
                        if (i == 2) {//fire at top adjacent
                            row = lastHitRow - 1;
                            col = lastHitCol;
                            if (row < 0) {//out of bounds on top
                                row = lastHitRow + 1;//change to fire below hit
                            }
                            state.shipHit(row, col, 0);
                        }
                        else if (i == 3) {//fire at bottom adjacent
                            row = lastHitRow + 1;
                            col = lastHitCol;
                            if (row > 9) {//out of bounds on bottom
                                break; //no spots left around hit;
                            }
                            state.shipHit(row, col, 0);
                        }
                    }
                    i++;
                    if (i > 3) {
                        break;//all 4 adjacent spots have been checked
                    }
                }
                //all spots around lastHit were checked, now go back to the firstHit and check around there
                //same algorithm for choosing location used above
                int j = 0;
                while (state.getPlayerID() == 1) {
                    if (j == 0) {//fire at right adjacent
                        row = firstHitRow;
                        col = firstHitCol + 1;
                        if (col > 9) {//out of bounds on right
                            col = firstHitCol - 1;//change to left of hit
                        }
                        state.shipHit(row, col, 0);
                    } else if (j == 1) {//fire at left adjacent
                        row = firstHitRow;
                        col = firstHitCol - 1;
                        if (col < 0) {//out of bounds on left
                            row = firstHitRow - 1;//change to fire at top of hit
                            col = firstHitCol;
                            if (row < 0) {//out of bounds on top
                                row = firstHitRow + 1; //change to bottom
                            }
                        }
                        state.shipHit(row, col, 0);
                    }
                    if (!goHorizontal) {
                        if (j == 2) {//fire at top adjacent
                            row = firstHitRow - 1;
                            col = firstHitCol;
                            if (row < 0) {//out of bounds on top
                                row = firstHitRow + 1;//change to fire below hit
                            }
                            state.shipHit(row, col, 0);
                        }
                        else if (j == 3) {//fire at bottom adjacent
                            row = firstHitRow + 1;
                            col = firstHitCol;
                            if (row > 9) {//out of bounds on bottom
                                break; //no spots left around hit;
                            }
                            state.shipHit(row, col, 0);
                        }
                    }
                    j++;
                    if (j > 3) {
                        break;
                    }
                }
            }
            else {//the user's ship may be vertical
                //Same algorithm used above, but instead the AI will choose a location above or below
                //the hit first, instead of the right and left
                int i = 0;
                while (state.getPlayerID() == 1) {
                    if (i == 0) {//fire at top adjacent
                        row = lastHitRow - 1;
                        col = lastHitCol;
                        if (row < 0) {//out of bounds on top
                            row = lastHitRow + 1;//change to fire below hit
                        }
                        state.shipHit(row, col, 0);
                    }
                    else if (i == 1) {//fire at bottom adjacent
                        row = lastHitRow + 1;
                        col = lastHitCol;
                        if (row > 9) {//out of bounds on bottom
                            row = lastHitRow;//change to right
                            col = lastHitCol + 1;
                            if (col > 9) {//out of bounds on right
                                col = lastHitCol - 1;//change to left
                            }
                        }
                        state.shipHit(row, col, 0);
                    }
                    if (!goVertical) {
                        if (i == 2) {//fire at right adjacent
                            row = lastHitRow;
                            col = lastHitCol + 1;
                            if (col > 9) {//out of bounds on right
                                col = lastHitCol - 1;//change to left of hit
                            }
                            state.shipHit(row, col, 0);
                        }
                        else if (i == 3) {//fire at left adjacent
                            row = lastHitRow;
                            col = lastHitCol - 1;
                            if (col < 0) {//out of bounds on left
                                break;//no more spots to check
                            }
                            state.shipHit(row, col, 0);
                        }
                    }
                    i++;
                    if (i > 3) {
                        break;//all 4 spots have been checked
                    }
                }
                //all spots around lastHit were checked, now go back to the firstHit and check around there
                //same algorithm for choosing location used above
                int j = 0;
                while (state.getPlayerID() == 1) {
                    if (j == 0) {//fire at top adjacent
                        row = firstHitRow - 1;
                        col = firstHitCol;
                        if (row < 0) {//out of bounds on top
                            row = firstHitRow + 1;//change to fire below hit
                        }
                        state.shipHit(row, col, 0);
                    }
                    else if (j == 1) {//fire at bottom adjacent
                        row = firstHitRow + 1;
                        col = firstHitCol;
                        if (row > 9) {//out of bounds on bottom
                            row = firstHitRow;//change to right
                            col = firstHitCol + 1;
                            if (col > 9) {//out of bounds on right
                                col = firstHitCol - 1;//change to left
                            }
                        }
                        state.shipHit(row, col, 0);
                    }
                    if (!goVertical) {
                        if (j == 2) {//fire at right adjacent
                            row = firstHitRow;
                            col = firstHitCol + 1;
                            if (col > 9) {//out of bounds on right
                                col = firstHitCol - 1;//change to left of hit
                            }
                            state.shipHit(row, col, 0);
                        }
                        else if (j == 3) {//fire at left adjacent
                            row = firstHitRow;
                            col = firstHitCol - 1;
                            if (col < 0) {//out of bounds on left
                                break;
                            }
                            state.shipHit(row, col, 0);
                        }
                    }
                    j++;
                    if (j > 3) {
                        break;
                    }
                }
            }
            if (state.getPlayerID() == 1){//if all spots around hit were checked but it's still AI's turn
                if (firstHitCol != lastHitCol && firstHitRow != lastHitRow) {//let first hit be last hit since
                    //all checks around each hit were checked
                    firstHitRow = lastHitRow;
                    firstHitCol = lastHitCol;
                }
                else {//We're done checking for more hits around a hit
                    goVertical = false;
                    stillOnTarget = false;
                }//generate a new random location
                while (state.getPlayerID() == 1) {
                    if (whichAI == 0) {//normal AI
                        row = (int) (Math.random() * 10);
                        col = (int) (Math.random() * 10);
                        state.shipHit(row, col, 0);
                    }
                    else {//hard AI
                        if (Math.random() > 0.5) { //70% chance of cheating
                            userGrid = state.getUserGrid();
                            findUserShip(userGrid);
                            row = userShipRow;
                            col = userShipCol;
                            if (!userShipFound) {//user ship was not found
                                row = (int) (Math.random() * 10);
                                col = (int) (Math.random() * 10);
                            }
                            state.shipHit(row, col, 0);
                        }
                        else {//don't cheat
                            row = (int) (Math.random() * 10);
                            col = (int) (Math.random() * 10);
                            state.shipHit(row, col, 0);
                        }
                    }
                }
            }
        }
        else {//not in killMode, then hunt by generating random locations
            while (state.getPlayerID() == 1) {
                if (whichAI == 0) {//normal AI
                    row = (int) (Math.random() * 10);
                    col = (int) (Math.random() * 10);
                    state.shipHit(row, col, 0);
                }
                else {//hard AI
                    if (Math.random() > 0.5) { //70% chance of cheating
                        userGrid = state.getUserGrid();
                        findUserShip(userGrid);
                        row = userShipRow;
                        col = userShipCol;
                        if (!userShipFound) {//user ship was not found
                            row = (int) (Math.random() * 10);
                            col = (int) (Math.random() * 10);
                        }
                        state.shipHit(row, col, 0);
                    }
                    else {//don't cheat
                        row = (int) (Math.random() * 10);
                        col = (int) (Math.random() * 10);
                        state.shipHit(row, col, 0);
                    }
                }
            }
        }
        if (state.getUserShipHit()){//If a user's ship was hit
            huntMode = false;//no longer in huntMode switch to killMode
            killMode = true;
            lastHitRow = row;//store location of lastHit
            lastHitCol = col;
            goVertical = twoInARowVertical(state,row,col);//check if the user's ship may be vertical
            if (whichAI != 0) {
                goHorizontal = twoInARowHorizontal(state, row, col);
            }
            if (stillOnTarget == false) {//When a person gets a hit that means there is probably more hits around
                //that location so, keep looking until all spots around hit have been checked
                stillOnTarget = true;
                firstHitRow = row;//store the location of first hit
                firstHitCol = col;
            }

        }
        else {//the AI missed
            if (stillOnTarget) {//if we're still searching for locations around hit, stay in killMode
                huntMode = false;
                killMode = true;
            } else { //switch to huntMode
                huntMode = true;
                killMode = false;
            }
        }

    }

    /**
     * Description: Method that checks if the AI has two vertical hits in a row. If they do, this means
     * the user's ship is probably vertical.
     *
     * CAVEAT:
     *
     * @param state     The game state variable passed in from BattleshipHumanPlayer
     * @param rowHit    Coordinates for the hit we are checking around
     * @param colHit
     */
    public boolean twoInARowVertical( BattleshipGameState state,int rowHit, int colHit) {
        userGrid = state.getUserGrid();//get the user's grid
        if (rowHit == 0) {//if hit at top only check below
            if (userGrid[rowHit+1][colHit] == 1) {
                return true;
            }
        }
        else if (rowHit == 9) {//if hit at bottom only check above
            if (userGrid[rowHit-1][colHit] == 1) {
                return true;
            }
        }
        //check above and below hit
        else if (userGrid[rowHit-1][colHit] == 1 || userGrid[rowHit+1][colHit] == 1)  {//if user hit 2 squares in a row vertically
            return true;
        }

        return false;
    }

    public boolean twoInARowHorizontal( BattleshipGameState state,int rowHit, int colHit) {
        userGrid = state.getUserGrid();//get the user's grid
        if (colHit == 0) {//if hit at leftmost only check to the right
            if (userGrid[rowHit][colHit+1] == 1) {
                return true;
            }
        }
        else if (colHit == 9) {//if hit at rightmost only check to the left
            if (userGrid[rowHit][colHit-1] == 1) {
                return true;
            }
        }
        //check right and left of hit
        else if (userGrid[rowHit][colHit-1] == 1 || userGrid[rowHit][colHit+1] == 1)  {//if user hit 2 squares in a row horizontally
            return true;
        }

        return false;
    }

    public void findUserShip(int[][] userGrid) {
        userShipFound = false;
        int i = 0;
        int j = 0;
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                if (userGrid[i][j] == 3) {
                    userShipFound = true;
                    break;
                }
            }
            if (j == 10) { j=9; }
            if (userGrid[i][j] == 3) {
                userShipFound = true;
                break;
            }
        }
        userShipRow = i;
        userShipCol = j;
    }


    public int getRow(){return row;}
    public int getCol(){return col;}

    public int getUserShipRow() {
        return userShipRow;
    }

    public int getUserShipCol() {
        return userShipCol;
    }
    public boolean getUserShipFound() { return userShipFound; }
}
