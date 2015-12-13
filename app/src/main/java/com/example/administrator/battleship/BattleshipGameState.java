package com.example.administrator.battleship;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * @author Nathan Camacho
 * @author Hashim AlJawad
 * @author Kelson Sipe
 *
 * @version  12/12/2015
 *
 * Description of BattleshipGameState class:
 * BattleshipGameState class inherits from the GameState class which is a part of the game framework.
 * It contains all informaton pertaining to the current state of the game so that the user may make
 * appropriate decisions. Also contains methods needed to modify the state based on user actions and
 * other events.
 *
 */

public class BattleshipGameState {
    //Instance variables
    private int player1Hits;
    private int player2Hits;

    //Variables to store locations of where AI ships are at, to be used to keep track of each ship's life
    private int carrierComputerRow;
    private int carrierComputerCol;
    private int battleshipComputerRow;
    private int battleshipComputerCol;
    private int destroyerComputerRow;
    private int destroyerComputerCol;
    private int submarineComputerRow;
    private int submarineComputerCol;
    private int boatComputerRow;
    private int boatComputerCol;
    private int boatDirection;
    private int submarineDirection;
    private int destroyerDirection;
    private int battleshipDirection;
    private int carrierDirection;
    private int computerShipDirection;

    private int playerID;//to keep track of whose turn it is
    private boolean AIshipHit = false;
    private boolean userShipHit = false;

    private int[][] userGrid;//grids to place both players ships on
    private int[][] computerGrid;

    private final int ROWS = 10;
    private final int COLS = 10;
    /**
     * Description: First constructor, takes no parameters. Initializes all instance variables
     * appropriately.
     */
    public BattleshipGameState(){

        player1Hits = 0;
        player2Hits = 0;
        playerID = 0;//ID 0 refers to the human player. 1 refers to the AI. Human will go first.

        //Initialize a 10x10 grid for each player
        //Each location in the 10x10 grid will contain a 0,1,2, or 3
        //0 - means that this coordinate has not been chosen by the user yet, and there is no ship located
        //on this coordinate (empty)
        //1 - corresponds to there being a hit on that coordinate
        //2 - corresponds to there being a miss on that coordinate
        //3 - corresponds to there being a ship on that coordinate
        //Here we initialize all coordinates in both grids to 0
        userGrid = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                userGrid[i][j] = 0;//0 means nothing there and it has not been touched yet
            }
        }
        computerGrid = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                computerGrid[i][j] = 0;
            }
        }
    }

    /**
     * Description: Makes a copy of an existing game state.
     *
     * @param object  existing game state
     */
    public BattleshipGameState(BattleshipGameState object){

        this.player1Hits = object.player1Hits;
        this.player2Hits = object.player2Hits;
        this.playerID = object.playerID;

        userGrid = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                userGrid[i][j] = object.userGrid[i][j];
            }
        }
        computerGrid = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                computerGrid[i][j] = object.computerGrid[i][j];
            }
        }

    }

    //Getters and setters
    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getPlayer2Hits() {
        return player2Hits;
    }

    public void setPlayer2Hits(int player2Hits) {
        this.player2Hits = player2Hits;
    }

    public int getPlayer1Hits() {
        return player1Hits;
    }

    public void setPlayer1Hits(int player1Hits) {
        this.player1Hits = player1Hits;
    }

    public boolean getAIShipHit(){ return AIshipHit; }
    public void setAIShipHit(boolean update){ AIshipHit = update; }

    public boolean getUserShipHit() {return userShipHit; }
    public void setUserShipHit(boolean update){ userShipHit = update; }

    public int[][] getUserGrid() {
        return userGrid;
    }

    public void setPosInUserGrid(int row, int col, int value) {
        this.userGrid[row][col] = value;
    }
    public int[][] getComputerGrid() {
        return computerGrid;
    }

    public void setPosInComputerGrid(int row, int col, int value) {
        this.computerGrid[row][col] = value;
    }

    /**
     * Description: Checks to see if one player has hit another player's ship. If this is true, then
     * this method will make the appropriate alterations to the game state. If the action from a player
     * is not a hit, then it must be a miss so it will call the shipMissed method. Also, a player's
     * turn will not change if they have not clicked on a valid location in the grid. i.e. if they clicked
     * on a coordinate they already hit or missed.
     *
     * CAVEAT:
     *
     * @param row coordinates for location to check
     * @param col
     */
    public void shipHit(int row, int col) {
            if (this.playerID == 0) {//human hit computer's ship
                //the player should be unable to click on a spot they already missed
                //their turn may not end until they hit or miss a new spot on the opponent's grid
                if (computerGrid[row][col] == 3) { //if a grid position equals 3, that means a ship is there
                    player1Hits = player1Hits + 1;//increment hits
                    AIshipHit = true;
                    this.playerID = 1;//change the turn of a player
                    computerGrid[row][col] = 1;//1 means there is a hit in this position
                } else {
                    this.shipMissed(row, col);//there is a miss in this location
                }
            } else { //computer hit human's ship
                if (userGrid[row][col] == 3) {//there is a ship in this location
                    player2Hits = player2Hits + 1;//increment hits
                    userShipHit = true;
                    this.playerID = 0;//change turns
                    userGrid[row][col] = 1;//1 means there is a hit in this position
                } else {
                    this.shipMissed(row, col);//there is a miss in this location
                }
            }
        printBoard();
    }

    /**
     * Description: This method alters a position in a player's grid so that there is now a "miss" in
     * that position
     *
     * @param row coordinates for location
     * @param col
     */
    public void shipMissed(int row,int col) {
        if (this.playerID == 0){//human missed in computer's grid
            if (computerGrid[row][col] == 0) {//0 means has not been clicked on already and no ships are there
                computerGrid[row][col] = 2;//change to a miss (2 corresponds to miss)
                AIshipHit = false;
                this.playerID = 1;//change turns
            }
        }
        else {//computer missed in human's grid
            if (userGrid[row][col] == 0) {
                userGrid[row][col] = 2;
                userShipHit = false;
                this.playerID = 0;
            }
        }

    }

    /**
     * Description: Method to set up the user's ships. Changes the value in the appropriate coordinate
     * from 0 to 3. 3 means there is a ship in this coordinate.
     *
     * CAVEAT: This method assumes it is being passed valid row and col positions so that it does not
     * go out of bounds of the 10x10 grid.
     *
     * @param shipNum   indicates which ship is being set up
     * @param row       coordinates to set up ship in grid
     * @param col
     * @param isHorizontal  a ship can only be set up horizontally or vertically
     */
    public void setUpUserShips(int shipNum, int row, int col, boolean isHorizontal) {
        if (shipNum == 1) {//carrier ship, 5 positions long
            if (isHorizontal == false) { //the ship is positioned vertically

                userGrid[row][col] = 3;
                userGrid[row + 1][col] = 3;
                userGrid[row + 2][col] = 3;
                userGrid[row + 3][col] = 3;
                userGrid[row + 4][col] = 3;

            } else {// the ship is positioned horizontally
                userGrid[row][col] = 3;
                userGrid[row][col + 1] = 3;
                userGrid[row][col + 2] = 3;
                userGrid[row][col + 3] = 3;
                userGrid[row][col + 4] = 3;
            }
        }
        if (shipNum == 2) {//battleship, 4 positions long
            if (isHorizontal == false) {

                userGrid[row][col] = 3;
                userGrid[row + 1][col] = 3;
                userGrid[row + 2][col] = 3;
                userGrid[row + 3][col] = 3;

            } else {
                userGrid[row][col] = 3;
                userGrid[row][col + 1] = 3;
                userGrid[row][col + 2] = 3;
                userGrid[row][col + 3] = 3;
            }
        }
        if (shipNum == 3) {//destroyer ship, 3 positions long
            if (isHorizontal == false) {

                userGrid[row][col] = 3;
                userGrid[row + 1][col] = 3;
                userGrid[row + 2][col] = 3;

            } else {
                userGrid[row][col] = 3;
                userGrid[row][col + 1] = 3;
                userGrid[row][col + 2] = 3;
            }
        }
        if (shipNum == 4) {//submarine, 3 positions long
            if (isHorizontal == false) {

                userGrid[row][col] = 3;
                userGrid[row + 1][col] = 3;
                userGrid[row + 2][col] = 3;
            } else {
                userGrid[row][col] = 3;
                userGrid[row][col + 1] = 3;
                userGrid[row][col + 2] = 3;
            }
        }
        if (shipNum == 5) {//pt boat, 2 positions long
            if (isHorizontal == false) {

                userGrid[row][col] = 3;
                userGrid[row + 1][col] = 3;

            } else {
                userGrid[row][col] = 3;
                userGrid[row][col + 1] = 3;
            }
        }

    }

    /**
     * Description: Method to set up the AI's ships.
     *
     * CAVEAT:
     *
     * @param AIships   array of AI's ships
     */
    public void setUpComputerShips(Ships[] AIships) {

        //sort the ships from largest size to smallest
        Arrays.sort(AIships, new Comparator<Ships>() {

            @Override
            public int compare(Ships s1, Ships s2) {
                return Integer.valueOf(s1.getSize()).compareTo(Integer.valueOf(s2.getSize()));
            }
        });

        //array to keep track if a spot was selected or not
        int[][] checked = new int[ROWS][COLS];
        Random ran = new Random();
        for (int i = 0; i <= AIships.length - 1; i++) {
            for (int j = 0; j < ROWS; j++)
                for (int k = 0; k < COLS; k++)
                    checked[j][k] = 0; // Unchecked position
            boolean shipPlaced = false;//if ship is in a valid position then true
            while (!shipPlaced) {//iterate until a ship has been placed
                  int row = ran.nextInt(ROWS);//random # from 0-9
                  int col = ran.nextInt(COLS);//random # from 0-9
                if (checked[row][col] == 0) {
                    checked[row][col] = 1; // Checked position
                    if (computerGrid[row][col] == 0) {//0 means spot is empty
                        computerShipDirection = ran.nextInt(4);//generate random # from 0-3 to determine which direction ship is facing
                        // 0 = North; 1 = East; 2 = South; 3 = West;
                        if (checkOverlap(AIships[i], row, col, computerShipDirection)) {//if ship can be placed then place it
                            place(AIships[i], row, col, computerShipDirection);
                            //store locations and direction of each ship
                            if(i == 0)
                            {
                                boatComputerRow = row;
                                boatComputerCol = col;
                                boatDirection = computerShipDirection;

                            }

                            if(i == 1)
                            {
                                submarineComputerRow = row;
                                submarineComputerCol = col;
                                submarineDirection = computerShipDirection;
                            }

                            if(i == 2)
                            {
                                destroyerComputerRow = row;
                                destroyerComputerCol = col;
                                destroyerDirection = computerShipDirection;
                            }

                            if(i == 3)
                            {
                                battleshipComputerRow = row;
                                battleshipComputerCol = col;
                                battleshipDirection = computerShipDirection;
                            }

                            if(i == 4)
                            {
                                carrierComputerRow = row;
                                carrierComputerCol = col;
                                carrierDirection = computerShipDirection;
                            }

                            shipPlaced = true;
                        }
                    }
                }
            }
        }
    }

    /**
     * Description: Method to place the AI's ships in the grid. Changes the value in the appropriate coordinate
     * from 0 to 3. 3 means there is a ship in this coordinate.
     *
     * CAVEAT:
     *
     * @param ship      ship to be placed
     * @param row
     * @param col
     * @param shipDirection     direction ship is facing
     */
    private void place(Ships ship, int row, int col, int shipDirection) {
        int size = ship.getSize();
        switch (shipDirection) {
            case 0: // North
                for (int  i = row; i >= row - (size - 1); i--)
                    computerGrid[i][col] = 3;
                break;

            case 1: // East
                for (int i = col; i <= col + (size - 1); i++)
                    computerGrid[row][i] = 3;
                break;

            case 2: // South
                for (int i = row; i <= row + (size - 1); i++)
                    computerGrid[i][col] = 3;
                break;

            default: // West
                for (int i = col; i >= col - (size - 1); i--)
                    computerGrid[row][i] = 3;
                break;
        }
    }

    /**
     * Description: Method to check if any ships are overlapping or if they are out of the grid.
     *
     * CAVEAT:
     *
     * @param ship      ship to be placed
     * @param row
     * @param col
     * @param shipDirection     direction ship is facing
     */
    private boolean checkOverlap(Ships ship, int row, int col, int shipDirection) {
        int size = ship.getSize();
        boolean validPosition = true;
        switch (shipDirection) {
            case 0: // North
                if (row - (size - 1) < 0) //not in valid position
                    validPosition = false;
                else
                    for (int  i = row; i >= row - (size - 1) && validPosition; i--)
                        validPosition = validPosition & (computerGrid[i][col] == 0);
                        //validPosition will stay true if all the slots where the ship will be placed on are empty
                        //otherwise it will change to false
                break;

            case 1: // East
                if (col + (size - 1) >= COLS)//not in valid position
                    validPosition = false;
                else
                    for (int i = col; i <= col + (size - 1) && validPosition; i++)
                        validPosition = validPosition & (computerGrid[row][i] == 0);
                //validPosition will stay true if all the slots where the ship will be placed on are empty
                //otherwise it will change to false
                break;

            case 2: // South
                if (row + (size - 1) >= ROWS)//not in valid position
                    validPosition = false;
                else
                    for (int i = row; i <= row + (size - 1) && validPosition; i++)
                        validPosition  = validPosition & (computerGrid[i][col] == 0);
                //validPosition will stay true if all the slots where the ship will be placed on are empty
                //otherwise it will change to false
                break;

            default: // West
                if (col - (size - 1) < 0)//not in valid position
                    validPosition = false;
                else
                    for (int i = col; i >= col - (size - 1) && validPosition; i--)
                        validPosition = validPosition & (computerGrid[row][i] == 0);
                //validPosition will stay true if all the slots where the ship will be placed on are empty
                //otherwise it will change to false
                break;
        }
        return validPosition;
    }

    //Method for us to check if board is being set up correctly in background
    public void printBoard() {
        System.out.println("\n\nAI's grid:\n");
        for (int i = 0; i < ROWS; i++)
            System.out.println(Arrays.toString(computerGrid[i]));
        System.out.println("\n\nUser's grid:\n");
        for (int i = 0; i < ROWS; i++)
            System.out.println(Arrays.toString(userGrid[i]));
    }


    public int getCarrierComputerRow() { return carrierComputerRow;}
    public int getCarrierComputerCol() { return carrierComputerCol; }

    public int getBattleshipComputerRow() { return battleshipComputerRow;}
    public int getBattleshipComputerCol() { return battleshipComputerCol; }

    public int getDestroyerComputerRow() { return destroyerComputerRow;}
    public int getDestroyerComputerCol() { return destroyerComputerCol; }

    public int getSubmarineComputerRow() { return submarineComputerRow;}
    public int getSubmarineComputerCol() { return submarineComputerCol; }

    public int getBoatComputerRow() { return boatComputerRow;}
    public int getBoatComputerCol()
    {
        return boatComputerCol;
    }

    public int getComputerShipDirection() { return computerShipDirection; }

    public int getBoatDirection() {
        return boatDirection;
    }

    public int getSubmarineDirection() {
        return submarineDirection;
    }

    public int getDestroyerDirection() {
        return destroyerDirection;
    }

    public int getBattleshipDirection() {
        return battleshipDirection;
    }

    public int getCarrierDirection() {
        return carrierDirection;
    }
}
