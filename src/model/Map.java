package model;

import model.cell.Cell;

public class Map {
    private Cell[][] cells;

    public void setCells(Cell[][] cells){
        this.cells=cells;
    }

    public boolean isWalkable(Point p) {
        boolean res;
        if(p.getX()>=0 && p.getY()>=0) {
            res = cells[p.getX()][p.getY()].isWalkable();
        }else{
            res = false;
        }
        return res;
    }
}
