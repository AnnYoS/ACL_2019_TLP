package model;

import math.Point;
import math.Vector;
import model.cell.Cell;
import model.cell.Warp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map {
    private Cell[][] cells;
    private int w;
    private int h;
    private List<Warp> warps = new ArrayList<>();

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

    public void setWarpLinks(HashMap<Integer, List<Warp>> map){
        for(int i=0; i<6; i++){
            if(map.get(i).size()==2){
                Warp w1 = map.get(i).get(0);
                Warp w2 = map.get(i).get(1);
                w1.setDest(getCellPos(w2));
                w2.setDest(getCellPos(w1));
                warps.add(w1);
                warps.add(w2);
            }
        }
    }

    public void reactivateWarps(Point heroPos){
        for (Warp w : warps){
            if(!w.equals(getCell(heroPos.getX(), heroPos.getY()))){
                w.activate();
            }
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

    public Point getCellPos(Cell c){
        for(int i=0; i<cells.length; i++){
            for(int j=0; j<cells[0].length; j++){
                if(c.equals(cells[i][j])){
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}
