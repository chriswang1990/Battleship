package battleship;

/**
 * @author Guanqing Hao & Minquan Wang
 */
public abstract class Ship {
	private int bowRow;
	private int bowColumn;
	protected int length;
	private boolean horizontal;
	protected boolean[] hit = new boolean[4];

	public Ship() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * returns bowRow
	 * @return bowRow
	 */
	public int getBowRow() {
		return bowRow;
	}

	/**
	 * Sets the value of bowRow
	 * @param bowRow
	 */
	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}

	/**
	 * returns bowColumn
	 * @return bowColumn
	 */
	public int getBowColumn() {
		return bowColumn;
	}

	/**
	 * sets the value of bowColumn
	 * @param bowColumn
	 */
	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}

	/**
	 * returns the length of this particular ship
	 * @return length of the ship
	 */
	public int getLength() {
		return length;
	}

	/**
	 * returns horizontal as boolean
	 * @return isHorizontal
	 */
	public boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * sets the value of instance variable horizontal
	 * @param horizontal
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}


	abstract String getShipType();

	/**
	 * returns true if it is okay to put a ship of certain length with its bow in this location, with the given orientation
	 * returns false otherwise
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return okToPlaceShipAt as boolean
	 */
	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean){
		boolean okToPlace = true;
		boolean[][] shadows = ocean.getShadow();
		if (horizontal){
			for (int i=0; i<this.getLength();i++){
				if (column+i>9){okToPlace = false;}
				else if (shadows[row][column+i]){okToPlace = false;}
			}
		}
		else{
			for (int i=0; i<this.getLength();i++){
				if (row+i>9){okToPlace = false;}
				else if (shadows[row+i][column]){okToPlace = false;}
			}
		}
		return okToPlace;
	}

	/**
	 * puts the ship on a certain spot in the ocean
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean){
		this.setHorizontal(horizontal);
		this.setBowRow(row);
		this.setBowColumn(column);
		if (!this.isHorizontal()){
			for (int i=0;i<this.getLength();i++){
				ocean.placeShip(row+i, column, this);
			}
		}
		else{
			for (int i=0;i<this.getLength();i++){
				ocean.placeShip(row, column+i, this);
			}
		}
	}


	/**
	 * returns true if the ship is hit and not sunk. marks that part of the ship as "hit"
	 * returns false otherwise
	 * @param row
	 * @param column
	 * @return shootAt as boolean
	 */
	public boolean shootAt(int row, int column){

		if (!this.getShipType().equals("empty") && !this.isSunk()) {

			if (this.isHorizontal()){
				for (int i=0; i<this.getLength();i++){
					if ((this.getBowRow() == row)&&(this.getBowColumn()+i==column)){
						this.hit[i] = true;
						return true;
					}
				}
			}
			else{
				for (int i=0; i<this.getLength();i++){
					if ((this.getBowRow()+i == row)&&(this.getBowColumn()==column)){
						this.hit[i] = true;
						return true;
					}
				}
			}
		}
		return false;

	}

	/**
	 * returns the boolean[] that shows which part of the ship is hit
	 * @return this.hit
	 */
	public boolean[] getHitArray(){
		return this.hit;
	}

	/**
	 * returns true if every part of the ship has been hit
	 * @return isSunk
	 */
	public boolean isSunk(){
		boolean isSunk = true;
		for (int i=0;i<this.getLength();i++){
			isSunk = isSunk&&this.hit[i];
		}
		return isSunk;
	}

	/**
	 * returns a single-char String to be used in the class 'Ocean'
	 * "x" if sunk, "S" if not sunk
	 */
	@Override
	public String toString(){
		if (this.isSunk()){
			return "x";
		}
		return "S";
	}


}
