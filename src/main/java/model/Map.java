package model;

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

    public boolean isWalkable(Vector p) {
        boolean res;
        if(p.getX()>=0 && p.getY()>=0 && p.getX()< cells.length && p.getY() < cells[1].length) {
            res = cells[(int) p.getX()][(int) p.getY()].isWalkable();
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
