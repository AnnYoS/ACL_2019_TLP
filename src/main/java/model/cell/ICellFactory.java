package model.cell;

import sun.misc.CEFormatException;

public interface ICellFactory {
    Cell createWall();
    Cell createGrass();
    Cell createTrap();
    Cell createChest();
}
