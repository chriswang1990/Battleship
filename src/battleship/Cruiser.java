package battleship;

public class Cruiser extends Ship {

    /**
     * constructor for subclass Cruiser
     */
    public Cruiser() {
        super();
        length = 3;
        for (int i = 0; i < 3; i++) {
            hit[i] = false;
        }
    }

    /**
     * override the getShipType method for subclass Cruiser
     */
    @Override
    public String getShipType() {
        return "cruiser";
    }


}
