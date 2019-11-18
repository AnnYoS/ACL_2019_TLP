package model;

import engine.Cmd;
import engine.Game;
import model.cell.Cell;
import model.person.Hero;
import model.person.Monster;
import model.person.strategy.*;

import java.util.ArrayList;
import java.util.List;

public class World implements Game {
    private Map map;
    private Hero hero;
    private List<Monster> monsterList;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setMonsterList(List<Monster> monsterList) {
        this.monsterList = monsterList;
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
        for (int i =0 ; i<monsterList.size();i++){
            Monster m = monsterList.get(i);
            if (m.getPos().equals(getHeroPos())){
                m.attack(hero);
                hero.attack(m);
                if(m.getLifepoints()<=0){
                    monsterList.remove(i);
                    i--;
                }
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
                break;
            }
            case DOWN: {
                Point old = getHeroPos();
                Point n = new Point(old.getX(), old.getY() + 1);
                moveHeroTo(n);
                break;
            }case LEFT: {
                Point old = getHeroPos();
                Point n = new Point(old.getX() - 1, old.getY());
                moveHeroTo(n);
                break;
            }case RIGHT: {
                Point old = getHeroPos();
                Point n = new Point(old.getX() + 1, old.getY());
                moveHeroTo(n);
                break;
            }
            case IDLE:
        }
        moveMonsters();
        monsterAttack();
    }

    @Override
    public boolean isFinished() {
        return hero.getLifepoints() <= 0;
    }
}
