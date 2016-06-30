package battleship;

public class EmptySea extends Ship{

	public EmptySea() {
		// TODO Auto-generated constructor stub
		super();
		this.length = 1;
		this.hit[0] = false;
	}
	
	@Override
	public boolean shootAt(int row, int column){
		this.hit[0] = true;
		return false;
	}
	
	@Override
	public boolean isSunk(){
		return false;
	}
	
	@Override
	public String toString(){
		return "-";
	}
	
	@Override
	String getShipType(){
		return "empty";
	}


}
