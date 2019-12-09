package model.cell;

public class CellFactory implements ICellFactory {
    private static final Wall NORMAL_WALL = new Wall(false);
    private static final Wall DESTRUCTIBLE_WALL = new Wall(true);
    private static final Grass GRASS = new Grass();
    private static final Trap TRAP = new Trap();
    private static final Chest CHEST = new Chest();
    private static final Sand SAND = new Sand();
    //private static final Warp WARP = new Warp();

    @Override
    public Cell createWall() {
        return NORMAL_WALL;
    }

    @Override
    public Cell createDestructibleWall() {
        return DESTRUCTIBLE_WALL;
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
        return new Warp();
    }

    @Override
    public Cell createSand() { return SAND;}


}
