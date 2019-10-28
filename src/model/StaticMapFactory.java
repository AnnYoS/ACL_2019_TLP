package model;

import model.cell.Cell;
import model.cell.Grass;

public class StaticMapFactory extends MapFactory{

    @Override
    public Map loadMap(){
        Map m = new Map();
        Cell[][] cells = new Cell[20][20];
        for(int i=0; i<20; i++){
            for(int j=0; j<20; j++){
                cells[i][j] = new Grass();
            }
        }

        m.setCells(cells);
        return m;
    }
}
