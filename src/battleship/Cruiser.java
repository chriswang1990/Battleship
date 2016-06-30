package battleship;

public class Cruiser extends Ship{

	/**
	 * constructor for subclass Cruiser
	 */
	public Cruiser() {
		// TODO Auto-generated constructor stub
		super();
		this.length = 3;
		for (int i=0; i<3; i++){
			this.hit[i] = false;
		}
	}
	
	/**
	 * override the getShipType method for subclass Cruiser 
	 */
	@Override
	public String getShipType(){
		return "cruiser";
	}
	

}
