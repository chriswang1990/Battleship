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
	Random random = new Random();
	private boolean[][] shadow;
	private Ship battleship;
	private Ship cruiser1, cruiser2;
	private Ship destroyer1, destroyer2, destroyer3;
	private Ship submarine1, submarine2, submarine3, submarine4;
	private ArrayList<Ship> allShips;

	/**
	 * constructor for ocean class
	 */
	public Ocean() {
		// TODO Auto-generated constructor stub
		//create the ships objects
		battleship = new Battleship();
		cruiser1 = new Cruiser();
		cruiser2 = new Cruiser();
		destroyer1 = new Destroyer();
		destroyer2 = new Destroyer();
		destroyer3 = new Destroyer();
		submarine1 = new Submarine();
		submarine2 = new Submarine();
		submarine3 = new Submarine();
		submarine4 = new Submarine();

		//put all the ships in a array list for further reference
		allShips = new ArrayList<Ship>();
		allShips.add(battleship);
		allShips.add(cruiser1);
		allShips.add(cruiser2);
		allShips.add(destroyer1);
		allShips.add(destroyer2);
		allShips.add(destroyer3);
		allShips.add(submarine1);
		allShips.add(submarine2);
		allShips.add(submarine3);
		allShips.add(submarine4);

		//initialize the ship array and shadow array
		ships = new Ship[10][10];
		shadow = new boolean[10][10];

		//fill ship array with empty sea
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				this.ships[i][j] = new EmptySea();
				this.ships[i][j].setBowRow(i);
				this.ships[i][j].setBowColumn(j);
				this.ships[i][j].setHorizontal(true);
				this.shadow[i][j] = false;
			}
		}
		//initialize the count number
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;

	}

	/**
	 * Place all ten ships randomly on the (initially empty) ocean
	 */
	public void placeAllShipsRandomly() {
		int row;
		int column;
		int trueOrFalse;
		for (Ship ship: allShips){
			row = (int) (Math.random() * 10);
			column = (int) (Math.random() * 10);
			trueOrFalse = (int) (Math.random() * 2);
			boolean horizontal = false;
			if (trueOrFalse == 1) {
				horizontal = true;
			}
			else {
				horizontal = false;
			}

			while (!ship.okToPlaceShipAt(row, column, horizontal, this)) {
				row = (int) (Math.random() * 10);
				column = (int) (Math.random() * 10);
				trueOrFalse = (int) (Math.random() * 2);
				if (trueOrFalse == 1) {
					horizontal = true;
				}
				else {
					horizontal = false;
				}
			}
			ship.placeShipAt(row, column, horizontal, this);

		}
	}

	/**
	 * Returns true if the given location contains a ship, false if it does not.
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean isOccupied(int row, int column) {
		if (this.ships [row][column].getShipType().equals("empty")) {
			return false;
		}
		return true;
	}

	/**
	 * Returns true if the given location contains a "real" ship, still afloat, false if it does not.
	 * updates the number of shots that have been fired, the number of hits, and the number of sunk ship.
	 * update the hit array of ship
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean shootAt(int row, int column) {
		int hit = 0;
		int sunkNum = 0;
		//update the hit num and hot fired
		if (isOccupied(row, column) && !ships[row][column].isSunk()) {
			this.hitCount += 1;
			hit = 1;
		}
		this.shotsFired += 1;
		//update the hit array of the ship
		this.ships[row][column].shootAt(row, column);
		//update the sunk number
		for (Ship ship: this.allShips) {
			if (ship.isSunk()){
				sunkNum += 1;
			}
		}
		this.shipsSunk = sunkNum;
		if (hit == 1) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the number of shots red (in this game).
	 * @return
	 */
	public int getShotsFired() {
		return this.shotsFired;
	}
	
	/**
	 * Returns the number of hits recorded (in this game).
	 * @return
	 */
	public int getHitCount() {
		return this.hitCount;
	}

	/**
	 * Returns the number of ships sunk (in this game).
	 * @return
	 */
	public int getShipsSunk() {
		return this.shipsSunk;
	}

	/**
	 * Returns true if all ships have been sunk, otherwise false.
	 * @return
	 */
	public boolean isGameOver() {
		
		boolean gameOver = true;
		for (Ship ship: this.allShips) {
			gameOver = gameOver && ship.isSunk();
		}
		return gameOver;
	}

	/**
	 * Returns the 10x10 array of ships.
	 * @return
	 */
	public Ship[][] getShipArray() {
		return this.ships;
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
				if (i == -1){				
					if (j > -1){
						s += "  " + j;
					}
				}
				else if (j == -1) {
					s += i + "  ";
				}
				else if (!this.isHit(i, j)) {
					s += "." + "  ";
				}
				else {
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
	 * @return
	 */
	public boolean[][] getShadow() {
		return this.shadow;
	}

	/**
	 * when put in one ship, shadow all its adjacent sea. Then the okToPrint function can make judgment and forbid ships to place on the shadow.  
	 */
	public void setShadow() {
		for (int i = 0; i < 10 ; i++){
			for (int j = 0; j < 10; j++) {
				if (this.isOccupied(i,j)) {
					for (int k = -1; k < 2; k++) {
						for (int l = -1; l <2; l++ ) {
							if ((i+k>=0) && (i+k<=9) && (j+l>=0) && (j+l <=9)) {
								shadow[i+k][j+l] = true;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * setter for ship class to place ship in the ocean
	 * @param row
	 * @param column
	 * @param ship
	 */
	public void placeShip(int row, int column, Ship ship) {
		this.ships[row][column] = ship;
		//update the shadow(places which don't allow ship to be placed) 
		this.setShadow();
	}

	/**
	 * all ships list getter for testing
	 * @return
	 */
	public ArrayList<Ship> getAllShips() {
		return this.allShips;
	}
	
	/**
	 * returns true if the location has been hit, otherwise returns false
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean isHit(int row, int column) {
		Ship ship = this.ships[row][column];
		int bowRow = ship.getBowRow();
		int bowColumn = ship.getBowColumn();
		
		if (ship.getShipType().equals("empty")) {
			return (ship.getHitArray()[0]);
		}
		else if (ship.isHorizontal()) {
			if (ship.getHitArray()[column - bowColumn]) {
				return true;
			}
			return false;
		}
		else {
			if (ship.getHitArray()[row - bowRow]) {
				return true;
			}
			return false;
		}
	}

}

