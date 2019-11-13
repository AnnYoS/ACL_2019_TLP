package model.cell;

public class CellFactory implements ICellFactory {
    public final char WALL = '#';

    @Override
    public Cell load(char cell) {
        switch (cell) {
            case WALL: {
                return new Wall();
            }
            default: {
                return new Grass();
            }
        }
    }

    @Override
    public void save(Cell cell) {

    }
}
