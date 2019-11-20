package model.cell;

import sun.misc.CEFormatException;

public interface ICellFactory {
    Cell createWall();
    Cell createGrass();
}
