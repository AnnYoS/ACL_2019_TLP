package model.cell;

public interface ICellFactory {
    Cell createWall();
    Cell createGrass();
    Cell createTrap();
    Cell createChest();
    Cell createWarp();
}
