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
        int[][] AIgrid = new int[10][10];

        //human ships
        test.setUpUserShips(1, 5, 5, false);//userGird[5-9][5] horizontally
        test.setUpUserShips(2, 0, 0, false);//userGrid[0-3][0] horizontally
        test.setUpUserShips(3, 5, 0, true);//userGrid[5][0-2] vertically
        test.setUpUserShips(4, 6, 0, true);//userGrid[6][0-2] vertically
        test.setUpUserShips(5, 4, 2, true);//userGrid[4][2-3] vertically

        Ships[] AIships = new Ships[] {
                new Ships(5),//carrier
                new Ships(4),//battleship
                new Ships(3),//destroyer
                new Ships(3),//sub
                new Ships(2),//pt boat
        };
        test.setUpComputerShips(AIships);

        //check that both players have 0 hits before the game starts
        assertEquals(0, test.getPlayer1Hits());
        assertEquals(0, test.getPlayer2Hits());


        //human turn
        test.shipHit(9, 5, 1);//guess

        //computer turn
        test.shipHit(1, 0, 2);//hit

        //human turn
        test.shipHit(2, 6, 6);//guess

        //computer turn
        test.shipHit(6, 2 ,4);//hit

        //human turn
        test.shipHit(9, 6, 1);//guess

        //computer turn
        test.shipHit(4, 3 ,5);//hit

        //human turn
        test.shipHit(6, 2, 4);//guess

        //computer turn
        test.shipHit(5, 1, 3);//hit

        //human turn
        test.shipHit(0, 0, 2);//guess

        //computer turn
        test.shipHit(5, 6, 7);//should miss

        //human turn
        test.shipHit(5, 1, 5);//guess

        //computer turn
        test.shipHit(4, 2, 5);//hit

        //loop through AI grid to see if they user got any hits (hit represented by 1)
        AIgrid = test.getComputerGrid();
        int userHits = 0;
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if (AIgrid[i][j] == 1) {
                    userHits++;
                }
            }
        }

        //this should be correct because the human got userHits hits and the computer got 5 hits
        assertEquals(userHits,test.getPlayer1Hits());
        assertEquals(5,test.getPlayer2Hits());

        //the human carrier ship life should be 5 because it did not get hit.
        assertEquals(5,test.getCarrierLife());

        //the human battleship life should be 3 because it got hit once.
        assertEquals(3,test.getBattleshipLife());


        //the human destroyer ship life should be 2 because it got hit once.
        assertEquals(2,test.getDestroyerLife());

        //the human submarine ship life should be 2 because it got hit once.
        assertEquals(2,test.getSubmarineLife());
        //assertEquals(2,test.getAISubmarineLife());

        //the human pt boat life should be 0 because it got hit twice.
        assertEquals(0,test.getPtBoatLife());

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
        assertEquals(2,testComputerArray[9][0]);
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
        test.setUpUserShips(2, 5, 5, true);
        test.setUpUserShips(3, 2, 2, false);
        test.setUpUserShips(4, 7, 3, true);
        test.setUpUserShips(5, 3, 6, true);

        int[][] testUserArray = test.getUserGrid();

        //If the ships have been set up correctly, the value 3 should be in the position where the ships
        //were set up
        assertEquals(3,testUserArray[3][3]);//Carrier
        assertEquals(3,testUserArray[3][4]);
        assertEquals(3,testUserArray[3][5]);
        assertEquals(3,testUserArray[3][6]);
        assertEquals(3,testUserArray[3][7]);

        assertEquals(3,testUserArray[5][5]);//Battleship
        assertEquals(3,testUserArray[5][6]);
        assertEquals(3,testUserArray[5][7]);
        assertEquals(3,testUserArray[5][8]);

        assertEquals(3,testUserArray[2][2]);//Destroyer
        assertEquals(3,testUserArray[3][2]);
        assertEquals(3,testUserArray[4][2]);

        assertEquals(3,testUserArray[7][3]);//Submarine
        assertEquals(3,testUserArray[7][4]);
        assertEquals(3,testUserArray[7][5]);

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
        int[][] AIgrid = new int[10][10];//array to store AIgrid

        //set up AI ships
        Ships[] AIships = new Ships[] {
                new Ships(5),//carrier
                new Ships(4),//battleship
                new Ships(3),//destroyer
                new Ships(3),//sub
                new Ships(2),//pt boat
        };
        test.setUpComputerShips(AIships);

        AIgrid = test.getComputerGrid();
        int counter = 0;
        //loop through grid, if there is a spot with a ship on it increment counter
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if (AIgrid[i][j] == 3) {
                    counter++;
                }
            }
        }
        //should be 17 spots with a ship on it since 5+4+3+3+2=17
        assertEquals(17,counter);

        //test case 2
        BattleshipGameState test2 = new BattleshipGameState();
        int[][] AIgrid2 = new int[10][10];//array to store AIgrid

        //set up AI ships
        Ships[] AIships2 = new Ships[] {
                new Ships(3),//carrier
                new Ships(3),//battleship
                new Ships(3),//destroyer
                new Ships(3),//sub
                new Ships(3),//pt boat
        };
        test2.setUpComputerShips(AIships2);

        AIgrid2 = test2.getComputerGrid();
        int counter2 = 0;
        //loop through grid, if there is a spot with a ship on it increment counter
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if (AIgrid2[i][j] == 3) {
                    counter2++;
                }
            }
        }
        //should be 15 spots with a ship on it since 3+3+3+3+3=15
        assertEquals(15,counter2);
    }

}