package model.cell;

public interface ICellFactory {
    Cell createWall();
    Cell createDestructibleWall();
    Cell createGrass();
    Cell createTrap();
    Cell createChest();
    Cell createWarp();
    Cell createSand();
}
