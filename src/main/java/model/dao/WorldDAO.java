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
import java.util.*;


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
        HashMap<Character, List<Warp>> warps = new HashMap<>();

        Hero hero = null;

        BufferedReader in = new BufferedReader(new FileReader(path));

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
                    case GRASS : {
                        cellLine.add(factory.createGrass());
                        break;
                    }
                    default: {
                        Warp w = (Warp) factory.createWarp();
                        cellLine.add(w);

                        /*
                        if the character is already present we only add the warp if there is one warp in the list
                        3 warps or more can't be bound together because that behaviour is undefined
                        only 2 warps may be linked
                         */
                        if(warps.containsKey(c)) {
                            List<Warp> tmp = warps.get(c);

                            if(tmp.size() == 1) {
                                Point p1 = tmp.get(0).getDest();
                                Point p2 = new Point(i, y);

                                tmp.get(0).setDest(p2);
                                w.setDest(p1);

                                tmp.add(w);
                            }
                        }
                        else {
                            /*
                            if the character is not already used for another warp, this means that the warp won't teleport
                            anywhere so the destination is the same as the entry point
                             */
                            List<Warp> tmp = new ArrayList<>(2);
                            tmp.add(w);

                            w.setDest(new Point(i, y));

                            warps.put(c, tmp);
                        }
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
                cellArray[j][i] = tmp.get(i);
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
