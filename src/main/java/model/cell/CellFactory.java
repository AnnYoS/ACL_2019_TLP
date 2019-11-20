package model.cell;

public class CellFactory implements ICellFactory {
    private static final Wall WALL = new Wall();
    private static final Grass GRASS = new Grass();

    @Override
    public Cell createWall() {
        return WALL;
    }

    @Override
    public Cell createGrass() {
        return GRASS;
    }
}
