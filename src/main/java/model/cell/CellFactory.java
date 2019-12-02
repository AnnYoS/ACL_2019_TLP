package model.cell;

public class CellFactory implements ICellFactory {
    private static final Wall WALL = new Wall();
    private static final Grass GRASS = new Grass();
    private static final Trap TRAP = new Trap();
    private static final Chest CHEST = new Chest();
    private static final Warp WARP = new Warp();

    @Override
    public Cell createWall() {
        return WALL;
    }

    @Override
    public Cell createGrass() {
        return GRASS;
    }

    @Override
    public Cell createTrap() {
        return TRAP;
    }

    @Override
    public Cell createChest() {
        return CHEST;
    }

    @Override
    public Cell createWarp() {
        return WARP;
    }
}
