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
        h = cells.length;

        w = cells[0].length;

        for(int i = 1; i < h; i++) {
            w = Math.max(w, cells[i].length);
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
            res = cells[y][x].isWalkable();
        }else{
            res = false;
        }
        return res;
    }

    public Cell getCell(int x, int y) {
        return cells[y][x];
    }

    public Point getCellPos(Cell c){
        for(int j = 0; j < h; j++){
            for(int i = 0; i < w; i++){
                if(c.equals(cells[j][i])){
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
