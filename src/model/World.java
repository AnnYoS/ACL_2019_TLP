package model;

import engine.Cmd;
import engine.Game;
import model.cell.Cell;
import model.person.Hero;
import model.person.Monster;
import model.person.strategy.FollowStrategy;

import java.util.ArrayList;
import java.util.List;

public class World implements Game {
    private Map map;
    private Hero hero;
    private List<Monster> monsterList;

    public World() {
        //map = new Map();
        StaticMapFactory staticMapFactory = new StaticMapFactory();
        map = staticMapFactory.loadMap();
        hero = new Hero(new Point(0, 0), 1);
        monsterList = new ArrayList<>();
        monsterList.add(new Monster(new Point(4, 11), new FollowStrategy()));
        monsterList.add(new Monster(new Point(0, 19), new RandomStrategy(), 1));
    }

    public boolean moveHeroTo(Point p) {
        boolean res;

        Point tmp = hero.getPos();

        res = Math.abs(p.getX() - tmp.getX()) <= 1 && Math.abs(p.getY() - tmp.getY()) <= 1 && map.isWalkable(p);

        if(res) {
            hero.setPos(p);
        }

        return res;
    }

    private void moveMonsters() {
        for (Monster m: monsterList) {
            Point p = m.getMove(map, hero.getPos());
            m.setPos(p);
        }
    }

    private void monsterAttack(){
        for (Monster m: monsterList){
            if (m.getPos().getX() == getHeroPos().getX() && m.getPos().getY() == getHeroPos().getY()){
                m.attack(hero);
            }
        }
    }

    public Point getHeroPos() {
        return hero.getPos();
    }

    public List<Monster> getMonsterList() {
        return monsterList;
    }

    public Cell[][] getMapCells(){return map.getCells();}

    @Override
    public void evolve(Cmd userCmd) {
        switch (userCmd) {
            case UP: {
                Point old = getHeroPos();
                Point n = new Point(old.getX(), old.getY() - 1);
                moveHeroTo(n);
                //moveMonsters();
                monsterAttack();
                break;
            }
            case DOWN: {
                Point old = getHeroPos();
                Point n = new Point(old.getX(), old.getY() + 1);
                moveHeroTo(n);
                //moveMonsters();
                monsterAttack();
                break;
            }case LEFT: {
                Point old = getHeroPos();
                Point n = new Point(old.getX() - 1, old.getY());
                moveHeroTo(n);
                //moveMonsters();
                monsterAttack();
                break;
            }case RIGHT: {
                Point old = getHeroPos();
                Point n = new Point(old.getX() + 1, old.getY());
                moveHeroTo(n);
                //moveMonsters();
                monsterAttack();
                break;
            }
            case IDLE:
        }
    }

    @Override
    public boolean isFinished() {
        return hero.getLifepoints() <= 0;
    }
}
