package model.dao;


import math.Point;
import model.Map;
import model.World;
import model.cell.Cell;
import model.cell.CellFactory;
import model.cell.Warp;
import model.person.Hero;
import model.person.Monster;
import model.person.strategy.FollowStrategy;
import model.person.strategy.RandomStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class WorldDAO implements IWorldDAO{
    private static final char RANDOM_STRAT = 'R';
    private static final char FOLLOW_STRAT = 'F';
    private static final char WALL = '#';
    private static final char TRAP = 'T';
    private static final char CHEST = 'C';
    private static final char HERO = 'H';
    private static final char GRASS = ' ';


    @Override
    public World load(String path) throws IOException {
        World res = new World();

        CellFactory factory = new CellFactory();

        List<List<Cell>> cells = new ArrayList<>();
        List<Monster> monsters = new ArrayList<>();
        //List<List<Point>> warpLinks = new ArrayList<>();
        HashMap<Integer, List<Warp>> warps= new HashMap<>();

        Hero hero = null;

        BufferedReader in = new BufferedReader(new FileReader(path));

        for(int i=0; i<6; i++){
            warps.put(i, new ArrayList<>());
        }

        int y = 0;
        int width = 0;
        String line;
        while ((line = in.readLine()) != null) {
            List<Cell> cellLine = new ArrayList<>();

            width = Math.max(line.length(), width);

            for(int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);

                switch (c) {
                    case RANDOM_STRAT: {
                        monsters.add(new Monster(new Point(i, y), new RandomStrategy(), 1));
                        cellLine.add(factory.createGrass());
                        break;
                    }
                    case FOLLOW_STRAT: {
                        monsters.add(new Monster(new Point(i, y), new FollowStrategy(), 1));
                        cellLine.add(factory.createGrass());
                        break;
                    }
                    case HERO : {
                        hero = new Hero(new Point(i, y), 50);
                        cellLine.add(factory.createGrass());
                        break;
                    }
                    case WALL : {
                        cellLine.add(factory.createWall());
                        break;
                    }
                    case TRAP: {
                        cellLine.add(factory.createTrap());
                        break;
                    }
                    case CHEST: {
                        cellLine.add(factory.createChest());
                        break;
                    }
                    //Les Warps
                    case '0': {
                        Cell w = factory.createWarp();
                        cellLine.add(w);
                        warps.get(0).add((Warp) w);
                        break;
                    }
                    case '1': {
                        Cell w = factory.createWarp();
                        cellLine.add(w);
                        warps.get(1).add((Warp) w);
                        break;
                    }
                    case '2': {
                        Cell w = factory.createWarp();
                        cellLine.add(w);
                        warps.get(2).add((Warp) w);
                        break;
                    }
                    case '3':{
                        Cell w= factory.createWarp();
                        cellLine.add(w);
                        warps.get(3).add((Warp)w);
                        break;
                    }
                    case '4':{
                        Cell w = factory.createWarp();
                        cellLine.add(w);
                        warps.get(4).add((Warp) w);
                        break;
                    }
                    case '5':
                        Cell w= factory.createWarp();
                        cellLine.add(w);
                        warps.get(5).add((Warp)w);
                        break;
                    case GRASS : {
                    }
                    default: {
                        cellLine.add(factory.createGrass());
                        break;
                    }
                }
            }
            cells.add(cellLine);
            y++;
        }

        if(hero == null) {
            hero = new Hero(new Point(0, 0), 50);
        }

        Cell[][] cellArray = new Cell[cells.size()][width];

        for(int j = 0; j < cellArray.length; j++) {
            List<Cell> tmp = cells.get(j);
            for(int i = 0; i < tmp.size(); i++) {
                cellArray[i][j] = tmp.get(i);
            }
        }

        Map map = new Map();
        map.setCells(cellArray);
        map.setWarpLinks(warps);

        res.setHero(hero);
        res.setMonsterList(monsters);
        res.setMap(map);

        return res;
    }

    @Override
    public void save(World world) {

    }
}
