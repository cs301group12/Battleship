package com.example.administrator.battleship;

import android.provider.Telephony;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sipek18 on 12/3/2015.
 */
public class BattleshipGameStateTest {

    /**
     * Description: This method tests both constructors in BattleshipGameState. Checks if all instance
     * variables have the appropriate values.
     *
     * @throws Exception
     */
    @Test
    public void testBattleshipGameState() throws Exception {
        //test 1st constructor
        BattleshipGameState test = new BattleshipGameState();
        assertEquals(0,test.getPlayerID());
        assertEquals(0,test.getPlayer1Hits());
        assertEquals(0,test.getPlayer2Hits());

        assertEquals(5,test.getCarrierLife());
        assertEquals(4,test.getBattleshipLife());
        assertEquals(3,test.getDestroyerLife());
        assertEquals(3,test.getSubmarineLife());
        assertEquals(2,test.getPtBoatLife());
        assertEquals(5,test.getAICarrierLife());
        assertEquals(4,test.getAIBattleshipLife());
        assertEquals(3,test.getAIDestroyerLife());
        assertEquals(3,test.getAISubmarineLife());
        assertEquals(2,test.getAIPtBoatLife());

        int[][] testArray = test.getUserGrid();
        boolean dummy = true;//dummy will change to false if there is a position in the userGrid not
        //equal to 0.
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (testArray[i][j] != 0) {//dummy will change to false if there is a position in the
                    // userGrid not equal to 0.
                    dummy = false;
                }
            }
        }
        assertEquals(true,dummy);

        int[][] testComputerArray = test.getComputerGrid();
        boolean dummy2 = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (testComputerArray[i][j] != 0) {//dummy will change to false if there is a position in the
                    // computerGrid not equal to 0.
                    dummy2 = false;
                }
            }
        }
        assertEquals(true,dummy2);

        //tests for 2nd constructor
        //to test 2nd constructor we will alter the variables from the game state object above and
        //create a new object passing in the altered gameState object as a parameter
        test.setPlayer1Hits(3);
        test.setPlayer2Hits(2);
        test.setPlayerID(1);

        test.setCarrierLife(3);
        test.setAIBattleshipLife(2);
        test.setSubmarineLife(1);
        test.setAIPtBoatLife(0);

        testArray[3][1] = 3;
        testArray[8][6] = 3;

        testComputerArray[9][0] = 3;
        testComputerArray[5][5] = 3;

        BattleshipGameState test2 = new BattleshipGameState(test);//2nd constructor

        assertEquals(1,test2.getPlayerID());
        assertEquals(3,test2.getPlayer1Hits());
        assertEquals(2,test2.getPlayer2Hits());

        assertEquals(3,test2.getCarrierLife());
        assertEquals(1,test2.getSubmarineLife());
        assertEquals(2,test2.getAIBattleshipLife());
        assertEquals(0,test2.getAIPtBoatLife());

        int[][] test2Array = test2.getUserGrid();//userGrid should be same as userGrid from test, if
        // copied correctly
        int counter1 = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (test2Array[i][j] == 3) {
                    counter1++;//increment counter if there is a position in the grid with the value 3
                }
            }
        }
        assertEquals(2,counter1);//should only be 2 positions in grid with value 3

        int[][] test2ComputerArray = test2.getComputerGrid();//computerGrid should be same as computerGrid
        // from test, if copied correctly
        int counter2 = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (test2ComputerArray[i][j] == 3) {//increment counter if there is a position in the grid with the value 3
                    counter2++;
                }
            }
        }
        assertEquals(2,counter2);//should only be 2 positions in grid with value 3


    }

    /**
     * Description: Tests the shipHit method in BattleshipGameState class
     *
     * @throws Exception
     */
    @Test
    public void testShipHit() throws Exception {
        BattleshipGameState test = new BattleshipGameState();

        //human ships
        test.setUpUserShips(1, 5, 5, true);//userGird[5-9][5] horizontally
        test.setUpUserShips(2, 0, 0, true);//userGrid[0-3][0] horizontally
        test.setUpUserShips(3, 5, 0, false);//userGrid[5][0-2] vertically
        test.setUpUserShips(4, 6, 0, false);//userGrid[6][0-2] vertically
        test.setUpUserShips(5, 4, 2, false);//userGrid[4][2-3] vertically

        //computer ships
//        test.setUpComputerShips();//computerGird[9][5-9] vertically
//        test.setUpComputerShips(2, 0, 0, true);//computerGird[0-3][2] horizontally
//        test.setUpComputerShips(3, 2, 4, true);//computerGrid[2-4][4] horizontally
//        test.setUpComputerShips(4, 6, 1, false);//computerGrid[6][1-3] vertically
//        test.setUpComputerShips(5, 5, 0, false);//computerGrid[5][0-1] vertically

        //check that both players have 0 hits before the game starts
        assertEquals(0,test.getPlayer1Hits());
        assertEquals(0,test.getPlayer2Hits());

        //human turn
        test.shipHit(9, 5, 1);//hit

        //computer turn
        test.shipHit(1, 0, 2);//hit

        //human turn
        test.shipHit(2, 6, 6);//should miss

        //computer turn
        test.shipHit(6, 2 ,4);//hit

        //human turn
        test.shipHit(9, 6, 1);//hit

        //computer turn
        test.shipHit(4, 3 ,5);//hit

        //human turn
        test.shipHit(6, 2, 4);//hit

        //computer turn
        test.shipHit(5, 1, 3);//hit

        //human turn
        test.shipHit(0, 0, 2);//hit

        //computer turn
        test.shipHit(5, 6, 7);//should miss

        //human turn
        test.shipHit(5, 1, 5);//hit

        //computer turn
        test.shipHit(4, 2, 5);//hit

        //this should be correct because the human got 5 hits and the computer got 5 hits
        assertEquals(5,test.getPlayer1Hits());
        assertEquals(5,test.getPlayer2Hits());

        //the computer carrier ship life should be 3 because it got hit twice.
        //the human carrier ship life should be 5 because it did not get hit.
        assertEquals(3,test.getAICarrierLife());
        assertEquals(5,test.getCarrierLife());

        //the computer battleship life should be 3 because it got hit once.
        //the human battleship life should be 3 because it got hit once.
        assertEquals(3,test.getAIBattleshipLife());
        assertEquals(3,test.getBattleshipLife());

        //the computer destroyer ship life should be 3 because it did not get hit.
        //the human destroyer ship life should be 2 because it got hit once.
        assertEquals(3,test.getAIDestroyerLife());
        assertEquals(2,test.getDestroyerLife());

        //the human submarine ship life should be 2 because it got hit once.
        //the computer submarine ship life should be 2 because it got hit once.
        assertEquals(2,test.getSubmarineLife());
        assertEquals(2,test.getAISubmarineLife());

        //the human pt boat life should be 0 because it got hit twice.
        //the computer pt boat life should be 1 because it got hit once.
        assertEquals(0,test.getPtBoatLife());
        assertEquals(1,test.getAIPtBoatLife());

    }

    /**
     * Description: Tests the shipMissed method in BattleshipGameState class.
     *
     * @throws Exception
     */
    @Test
    public void testShipMissed() throws Exception {
        BattleshipGameState test = new BattleshipGameState();

        //human turn
        test.shipMissed(3,8);//should change the value in this position in computerGrid to 2

        //computer turn
        test.shipMissed(0,0);//should change the value in this position in userGrid to 2

        //human turn
        test.shipMissed(3,8);//should not do anything, since this spot is already a miss. Still human's turn.

       // test.setUpComputerShips(2,9,0,false);//place a ship in the position computerGrid[9][0]

        //still human turn
        test.shipHit(9, 0, 3);//should change the value in this position in computerGrid to 1 since it is a hit

        //computer turn
        test.shipMissed(5,4);//should change the value in this position in userGrid to 2

        //human turn
        test.shipMissed(9,0);//should not change anything since the value in this position is 1. We
        //dont want to change a hit to a miss


        int[][] testComputerArray = test.getComputerGrid();//get both players' grids
        int[][] testUserArray = test.getUserGrid();


        assertEquals(2,testComputerArray[3][8]);
        assertEquals(1,testComputerArray[9][0]);
        assertEquals(2,testUserArray[0][0]);
        assertEquals(2,testUserArray[5][4]);
    }

    /**
     * Description: Tests setUpUserShips in BattleshipGameState class.
     *
     * @throws Exception
     */
    @Test
    public void testSetUpUserShips() throws Exception {
        BattleshipGameState test = new BattleshipGameState();

        //Set's up the user's ships.
        test.setUpUserShips(1,3,3,true);
        test.setUpUserShips(2, 5, 5, false);
        test.setUpUserShips(3, 2, 2, true);
        test.setUpUserShips(4, 8, 3, false);
        test.setUpUserShips(5, 3, 6, false);

        int[][] testUserArray = test.getUserGrid();

        //If the ships have been set up correctly, the value 3 should be in the position where the ships
        //were set up
        assertEquals(3,testUserArray[3][3]);//Carrier
        assertEquals(3,testUserArray[4][3]);
        assertEquals(3,testUserArray[5][3]);
        assertEquals(3,testUserArray[6][3]);
        assertEquals(3,testUserArray[7][3]);

        assertEquals(3,testUserArray[5][5]);//Battleship
        assertEquals(3,testUserArray[5][6]);
        assertEquals(3,testUserArray[5][7]);
        assertEquals(3,testUserArray[5][8]);

        assertEquals(3,testUserArray[2][2]);//Destroyer
        assertEquals(3,testUserArray[3][2]);
        assertEquals(3,testUserArray[4][2]);

        assertEquals(3,testUserArray[8][3]);//Submarine
        assertEquals(3,testUserArray[8][4]);
        assertEquals(3,testUserArray[8][5]);

        assertEquals(3,testUserArray[3][6]);//PTBoat
        assertEquals(3,testUserArray[3][7]);




    }

    /**
     * Description: Tests setUpComputerShips in BattleshipGameState class.
     *
     * @throws Exception
     */
    @Test
    public void testSetUpComputerShips() throws Exception {
        BattleshipGameState test = new BattleshipGameState();

        //Set's up the computer's ships.
//        test.setUpComputerShips(1,3,3,true);
//        test.setUpComputerShips(2,6,7,true);
//        test.setUpComputerShips(3,3,7,false);
//        test.setUpComputerShips(4,4,6,false);
//        test.setUpComputerShips(5, 2, 5, true);

        int[][] testComputerArray = test.getComputerGrid();

        //If the ships have been set up correctly, the value 3 should be in the position where the ships
        //were set up
        assertEquals(3, testComputerArray[3][3]);//Carrier
        assertEquals(3, testComputerArray[4][3]);
        assertEquals(3, testComputerArray[5][3]);
        assertEquals(3, testComputerArray[6][3]);
        assertEquals(3, testComputerArray[7][3]);

        assertEquals(3, testComputerArray[6][7]);//Battleship
        assertEquals(3, testComputerArray[7][7]);
        assertEquals(3, testComputerArray[8][7]);
        assertEquals(3, testComputerArray[9][7]);

        assertEquals(3, testComputerArray[3][7]);//Destroyer
        assertEquals(3, testComputerArray[3][8]);
        assertEquals(3, testComputerArray[3][9]);

        assertEquals(3, testComputerArray[4][6]);//Submarine
        assertEquals(3, testComputerArray[4][7]);
        assertEquals(3, testComputerArray[4][8]);

        assertEquals(3, testComputerArray[2][5]);//PTBoat
        assertEquals(3, testComputerArray[3][5]);


    }

}