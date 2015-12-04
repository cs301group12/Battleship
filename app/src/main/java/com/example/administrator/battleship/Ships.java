package com.example.administrator.battleship;

/**
 * @author Nathan Camacho
 * @author Hashim AlJawad
 * @author Kelson Sipe
 *
 * @version  12/1/2015
 *
 * Description of Ship class:
 * This class represents a ship with a certain size
 *
 */
public class Ships {
    private int size;//size of ship
    //Carrier has a size of 5
    //Battleship has a size of 4
    //Destroyer has a size of 3
    //Submarine has a size of 3
    //PT Boat has a size of 2

    public Ships (int size) {
        this.size = size;
    }

    public int getSize(){ return this.size; }
}
