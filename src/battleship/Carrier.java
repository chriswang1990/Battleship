package battleship;

public class Carrier extends Ship{
    /**
     * constructor for subclass Carrier
     */
    public Carrier() {
        length = 5;
        for (int i = 0; i < 5; i++) {
            hit[i] = false;
        }
    }

    /**
     * override the getShipType method for subclass submarine
     */
    @Override
    public String getShipType() {
        return "carrier";
    }
}
