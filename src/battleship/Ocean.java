package battleship;

import java.util.*;

/**
 * @author Guanqing Hao & Minquan Wang
 */
public class Ocean {
    private Ship[][] ships;
    private int shotsFired;
    private int hitCount;
    private int shipsSunk;
    private boolean[][] shadow;
    private ArrayList<Ship> allShips;

    /**
     * constructor for ocean class
     */
    public Ocean() {
        //create the ships objects
        Ship carrier = new Carrier();
        Ship battleship = new Battleship();
        Ship cruiser1 = new Cruiser();
        Ship cruiser2 = new Cruiser();
        Ship destroyer1 = new Destroyer();
        Ship destroyer2 = new Destroyer();
        Ship destroyer3 = new Destroyer();

        //put all the ships in a array list for further reference
        allShips = new ArrayList<>();
        allShips.add(battleship);
        allShips.add(cruiser1);
        allShips.add(cruiser2);
        allShips.add(destroyer1);
        allShips.add(destroyer2);
        allShips.add(destroyer3);
        allShips.add(carrier);

        //initialize the ship array and shadow array
        ships = new Ship[10][10];
        shadow = new boolean[10][10];

        //fill ship array with empty sea
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ships[i][j] = new EmptySea();
                ships[i][j].setBowRow(i);
                ships[i][j].setBowColumn(j);
                ships[i][j].setHorizontal(true);
                shadow[i][j] = false;
            }
        }
        //initialize the count number
        shotsFired = 0;
        hitCount = 0;
        shipsSunk = 0;
    }

    /**
     * Place all ten ships randomly on the (initially empty) ocean
     */
    public void placeAllShipsRandomly() {
        int row;
        int column;
        int trueOrFalse;
        for (Ship ship : allShips) {
            row = (int) (Math.random() * 10);
            column = (int) (Math.random() * 10);
            trueOrFalse = (int) (Math.random() * 2);
            boolean horizontal = (trueOrFalse == 1);

            while (!ship.okToPlaceShipAt(row, column, horizontal, this)) {
                row = (int) (Math.random() * 10);
                column = (int) (Math.random() * 10);
                trueOrFalse = (int) (Math.random() * 2);
                horizontal = (trueOrFalse == 1);
            }
            ship.placeShipAt(row, column, horizontal, this);
        }
    }

    /**
     * Returns true if the given location contains a ship, false if it does not.
     */
    public boolean isOccupied(int row, int column) {
        return !ships[row][column].getShipType().equals("empty");
    }

    /**
     * Returns true if the given location contains a "real" ship, still afloat, false if it does not.
     * updates the number of shots that have been fired, the number of hits, and the number of sunk ship.
     * update the hit array of ship
     */
    public boolean shootAt(int row, int column) {
        int hit = 0;
        int sunkNum = 0;
        //update the hit num and hot fired
        if (isOccupied(row, column) && !ships[row][column].isSunk()) {
            hitCount += 1;
            hit = 1;
        }
        shotsFired += 1;
        //update the hit array of the ship
        ships[row][column].shootAt(row, column);
        //update the sunk number
        for (Ship ship : allShips) {
            if (ship.isSunk()) {
                sunkNum += 1;
            }
        }
        shipsSunk = sunkNum;
        return hit == 1;
    }

    /**
     * Returns the number of shots red (in this game).
     */
    public int getShotsFired() {
        return shotsFired;
    }

    /**
     * Returns the number of hits recorded (in this game).
     */
    public int getHitCount() {
        return hitCount;
    }

    /**
     * Returns the number of ships sunk (in this game).
     */
    public int getShipsSunk() {
        return shipsSunk;
    }

    /**
     * Returns true if all ships have been sunk, otherwise false.
     */
    public boolean isGameOver() {
        return (shipsSunk == allShips.size());
    }

    /**
     * Returns the 10x10 array of ships.
     */
    public Ship[][] getShipArray() {
        return ships;
    }

    /**
     * Prints the ocean.
     * Use 'S' to indicate a location that you have fired upon and hit a (real) ship
     * '-' indicate a location that you have fired upon and found nothing there
     * 'x' indicate a location containing a sunken ship
     * '.' indicate a location that you have never fired upon.
     */
    public void print() {
        String s = " ";
        int i;
        int j;
        for (i = -1; i < 10; i++) {
            for (j = -1; j < 10; j++) {
                if (i == -1) {
                    if (j > -1) {
                        s += "  " + j;
                    }
                } else if (j == -1) {
                    s += i + "  ";
                } else if (!isHit(i, j)) {
                    s += "." + "  ";
                } else {
                    s += ships[i][j].toString() + "  ";
                }
            }
            s += "\n";
        }
        System.out.println(s);
    }


    ////////////////////////////////////////////////additional helper functions//////////////////////////

    /**
     * return the shadow array (boolean[10][10] for okToPlaceAt method
     */
    public boolean[][] getShadow() {
        return shadow;
    }

    /**
     * when put in one ship, shadow all its adjacent sea. Then the okToPrint function can make judgment and forbid ships to place on the shadow.
     */
    public void setShadow() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (isOccupied(i, j)) {
                    for (int k = -1; k < 2; k++) {
                        for (int l = -1; l < 2; l++) {
                            if ((i + k >= 0) && (i + k <= 9) && (j + l >= 0) && (j + l <= 9)) {
                                shadow[i + k][j + l] = true;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * setter for ship class to place ship in the ocean
     */
    public void placeShip(int row, int column, Ship ship) {
        ships[row][column] = ship;
        //update the shadow(places which don't allow ship to be placed)
        setShadow();
    }

    /**
     * all ships list getter for testing
     */
    public ArrayList<Ship> getAllShips() {
        return allShips;
    }

    /**
     * returns true if the location has been hit, otherwise returns false
     */
    public boolean isHit(int row, int column) {
        Ship ship = ships[row][column];
        int bowRow = ship.getBowRow();
        int bowColumn = ship.getBowColumn();

        if (ship.getShipType().equals("empty")) {
            return (ship.getHitArray()[0]);
        } else if (ship.isHorizontal()) {
            return ship.getHitArray()[column - bowColumn];
        } else {
            return ship.getHitArray()[row - bowRow];
        }
    }
}

