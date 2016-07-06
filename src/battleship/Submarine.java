package battleship;

public class Submarine extends Ship {

    /**
     * constructor for subclass Submarine
     */
    public Submarine() {
        length = 1;
        hit[0] = false;
    }

    /**
     * override the getShipType method for subclass submarine
     */
    @Override
    public String getShipType() {
        return "submarine";
    }

}
