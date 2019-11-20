package model;

import engine.Cmd;
import engine.Game;
import model.cell.Cell;
import model.person.Hero;
import model.person.Monster;

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

    public boolean moveHeroTo(Vector p) {
        boolean res;

        Vector tmp = hero.getPos();

        res = Math.abs(p.getX() - tmp.getX()) <= 1 && Math.abs(p.getY() - tmp.getY()) <= 1 && map.isWalkable(p);

        if(res) {
            hero.setPos(p);
        }

        return res;
    }

    private void calcMonsterSpeeds() {
        for (Monster m: monsterList) {
            m.calcSpeed(map, hero.getPos());
        }
    }

    private void moveMonsters(long dt) {
        for(Monster m : monsterList) {
            m.evolve(map, dt);
        }
    }

    private void monsterAttack(){
        for (int i =0 ; i < monsterList.size();i++){
            Monster m = monsterList.get(i);
            if (m.getPos().integetManhattanDistance(getHeroPos()) == 0){
                m.attack(hero);
                hero.attack(m);
                if(m.getLifepoints()<=0){
                    monsterList.remove(i);
                    i--;
                }
            }
        }
    }

    public Vector getHeroPos() {
        return hero.getPos();
    }

    public List<Monster> getMonsterList() {
        return monsterList;
    }

    public Cell[][] getMapCells(){return map.getCells();}

    @Override
    public void evolve(long dt) {
        moveMonsters(dt);
        hero.evolve(map, dt);
        monsterAttack();
    }

    @Override
    public void events(Cmd c) {
        switch (c) {
            case UP: {
                /*Vector old = getHeroPos();
                Vector n = new Vector(old.getX(), old.getY() - 1);
                moveHeroTo(n);*/
                hero.setSpeed(new Vector(0, -Hero.SPEED));
                break;
            }
            case DOWN: {
                /*Vector old = getHeroPos();
                Vector n = new Vector(old.getX(), old.getY() + 1);
                moveHeroTo(n);*/
                hero.setSpeed(new Vector(0, Hero.SPEED));
                break;
            }case LEFT: {
                /*Vector old = getHeroPos();
                Vector n = new Vector(old.getX() - 1, old.getY());
                moveHeroTo(n);*/
                hero.setSpeed(new Vector(-Hero.SPEED, 0));
                break;
            }case RIGHT: {
                /*Vector old = getHeroPos();
                Vector n = new Vector(old.getX() + 1, old.getY());
                moveHeroTo(n);*/
                hero.setSpeed(new Vector(Hero.SPEED, 0));
                break;
            }
            case IDLE: {
                hero.setSpeed(new Vector(0, 0));
            }
        }
        calcMonsterSpeeds();
    }

    @Override
    public boolean isFinished() {
        return hero.getLifepoints() <= 0;
    }
}
