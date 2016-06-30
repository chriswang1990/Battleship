package battleship;

public class Destroyer extends Ship{

	/**
	 * constructor for subclass Destroyer
	 */
	public Destroyer() {
		// TODO Auto-generated constructor stub
		this.length = 2;
		for (int i=0; i<2; i++){
			this.hit[i] = false;
		}
	}
	
	/**
	 * override the getShipType method for subclass Destroyer 
	 */
	@Override
	public String getShipType(){
		return "destroyer";
	}

}
