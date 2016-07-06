package battleship;

public class Battleship extends Ship {

    /**
     * constructor for the battleship subclass
     */
    public Battleship() {
        super();
        length = 4;
        for (int i = 0; i < 4; i++) {
            hit[i] = false;
        }
    }

    /**
     * override the getShipType method for subclass battleship
     */
    @Override
    public String getShipType() {
        return "battleship";
    }


}
