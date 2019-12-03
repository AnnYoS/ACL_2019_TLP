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
        h = cells.length;

        w = cells[0].length;

        for(int i = 1; i < h; i++) {
            w = Math.max(w, cells[i].length);
        }
    }

    public boolean isWalkable(Point p) {
        boolean res;
        int x = p.getX();
        int y = p.getY();

        if(x >= 0 && y >= 0 && x < w && y < h) {
            res = cells[y][x].isWalkable();
        }else{
            res = false;
        }
        return res;
    }

    public Cell getCell(int x, int y) {
        return cells[y][x];
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}
