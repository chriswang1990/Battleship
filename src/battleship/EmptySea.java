package battleship;

public class EmptySea extends Ship {

    public EmptySea() {
        super();
        length = 1;
        hit[0] = false;
    }

    @Override
    public boolean shootAt(int row, int column) {
        hit[0] = true;
        return false;
    }

    @Override
    public boolean isSunk() {
        return false;
    }

    @Override
    public String toString() {
        return "-";
    }

    @Override
    String getShipType() {
        return "empty";
    }


}
