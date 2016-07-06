package battleship;

public class Carrier extends Ship{
    /**
     * constructor for subclass Carrier
     */
    public Carrier() {
        this.length = 5;
        for (int i = 0; i < 5; i++) {
            this.hit[i] = false;
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
