package battleship;

public class Submarine extends Ship{

	/**
	 * constructor for subclass Submarine
	 */
	public Submarine() {
		this.length = 1;
		this.hit[0] = false;
	}
	
	/**
	 * override the getShipType method for subclass submarine
	 */
	@Override
	public String getShipType(){
		return "submarine";
	}

}
