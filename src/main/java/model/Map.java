package model;

import math.Point;
import math.Vector;
import model.cell.Cell;

public class Map {
    private Cell[][] cells;
    private int w;
    private int h;

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells){
        this.cells=cells;
        w = cells.length;

        h = cells[0].length;

        for(int i = 1; i < w; i++) {
            h = Math.max(h, cells[i].length);
        }
    }

    public boolean isWalkable(Point p) {
        boolean res;
        int x = p.getX();
        int y = p.getY();

        if(x >= 0 && y >= 0 && x < w && y < h) {
            res = cells[x][y].isWalkable();
        }else{
            res = false;
        }
        return res;
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}
