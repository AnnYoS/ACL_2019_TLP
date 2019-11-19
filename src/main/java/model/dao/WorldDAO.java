package model.dao;


import model.Vector;
import model.World;
import model.person.Hero;
import model.person.Monster;
import model.person.strategy.FollowStrategy;
import model.person.strategy.MonsterStrategy;
import model.person.strategy.RandomStrategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


public class WorldDAO implements IWorldDAO{
    private final String RANDOM_STRAT = "R";
    private final String FOLLOW_STRAT = "F";

    @Override
    public World load(int lvl) {
        World world= new World();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File("levels/lvl"+lvl+".perso"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader  = new BufferedReader(fileReader);
        String buf;

        try {
            buf = bufferedReader.readLine();
            String[] sp = buf.split(" ");
            int x = Integer.valueOf(sp[0]);
            int y = Integer.valueOf(sp[1]);
            int lp = Integer.valueOf(sp[2]);
            world.setHero(new Hero(new Vector(x,y), lp));
        }catch (Exception e){
            e.printStackTrace();
        }

        ArrayList monsters = new ArrayList<Monster>();

        try {
            while((buf = bufferedReader.readLine()) != null){
                String[] sp = buf.split(" ");
                int x = Integer.valueOf(sp[0]);
                int y = Integer.valueOf(sp[1]);

                MonsterStrategy strat = null;
                switch(sp[2]){
                    case RANDOM_STRAT :{
                        strat = new RandomStrategy();
                        break;
                    }
                    case FOLLOW_STRAT :{
                        strat = new FollowStrategy();
                        break;
                    }
                }
                int lp = Integer.valueOf(sp[3]);
                monsters.add(new Monster(new Vector(x,y),strat ,lp));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        world.setMonsterList(monsters);
        world.setMap(DAOFactory.getInstance().getMapDAO().load(lvl));
        return world;
    }

    @Override
    public void save(World world) {

    }
}
