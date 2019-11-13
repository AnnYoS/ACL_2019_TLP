package model.dao;

import model.Map;
import model.cell.Cell;
import model.cell.CellFactory;
import model.cell.Grass;
import model.cell.Wall;

import java.io.*;

public class MapDAO implements IMapDAO{
    private CellFactory cellFactory;

    public MapDAO(){
        cellFactory = new CellFactory();
    }

    @Override
    public Map load(int lvl) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File("levels/lvl"+lvl+".map"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader  = new BufferedReader(fileReader);
        String buf;

        Map map = new Map();
        Cell[][] cells = new Cell[20][20];

        int i = 0;
        try {
            while ((buf = bufferedReader.readLine()) != null) {
                for (int j = 0; j < buf.length(); j++) {
                    cells[i][j] = cellFactory.load(buf.charAt(j));
                }
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        map.setCells(cells);

        return map;
    }

    @Override
    public void save(Map map) {

    }
}
