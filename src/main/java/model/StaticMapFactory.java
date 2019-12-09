package model;

import model.cell.Cell;
import model.cell.Grass;
import model.cell.Wall;

public class StaticMapFactory extends MapFactory{

    @Override
    public Map loadMap(){
        Map m = new Map();

        //map remplie de cases d'herbe
        Cell[][] cells = new Cell[20][20];
        for(int i=0; i<20; i++){
            for(int j=0; j<20; j++){
                cells[i][j] = new Grass();
            }
        }

        //ajout de murs
        cells[1][4]= new Wall(false);
        cells[5][4]= new Wall(false);
        cells[6][2]= new Wall(false);
        cells[8][8]= new Wall(false);

        //ajout de murs pour les bords
        for (int i = 0; i <20 ; i++) {
            cells[i][0] = new Wall(false);
            cells[i][19] = new Wall(false);
            cells[0][i] = new Wall(false);
            if(i != 10){
                cells[19][i] = new Wall(false);
            }
        }

        m.setCells(cells);
        return m;
    }
}
