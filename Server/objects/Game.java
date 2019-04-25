package objects;

import java.util.Random;


public class Game{
    /*  
        The board is going to be a matrix composed of integers
        if a cell in board is:
            0: then it's empty
            1: there's a vessel
            2: it has been shot
        Ships:
            The game will consists of 5 ships:
            Carrier (occupies 5 spaces), Battleship (4), Cruiser (3), Submarine (3), and Destroyer (2).
            the ships will be places randomly on the screen
        
    */

    private int[][] board;
    private int dimension;
    private Random rand;

    public Game(){
        // We use random to create a matrix with dimension between 8 to 24
        rand = new Random();
        this.dimension = rand.nextInt(16)+9;

        // we create a board with thouse dimensions and then we populate it
        board = new int[dimension][dimension];

    }

    public int[][] populate(int[][] emptyBoard){

        return null;
    }
}