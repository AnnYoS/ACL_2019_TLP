package model.cell;

public class Wall implements Cell {
    @Override
    public boolean isWalkable() {
        return false;
    }
}
