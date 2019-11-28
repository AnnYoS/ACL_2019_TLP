package model;

import engine.Cmd;
import engine.Game;
import math.Point;
import math.Vector;
import model.person.Hero;
import model.person.Monster;

import java.util.List;

public class World implements Game {
    private Map map;
    private Hero hero;
    private boolean gameOver = false;
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

    private void applyCellEffectOnPerson() {
        Point p = hero.getPos();

        int x = (int) p.getX();
        int y = (int) p.getY();

        map.getCell(x, y).applyDamage(hero);

        for(Monster m : monsterList) {
            x = (int) m.getPos().getX();
            y = (int) m.getPos().getY();

            map.getCell(x, y).applyDamage(m);
        }
    }

    private void removeDeadMonsters() {
        for(int i = monsterList.size() - 1; i >= 0; i--) {
            Monster m = monsterList.get(i);

            if(m.getLifepoints() <= 0) {
                monsterList.remove(i);
            }
        }
    }

    private void monsterAttack(){
        for (int i =0 ; i < monsterList.size();i++){
            Monster m = monsterList.get(i);
            if (m.getPos().manhattanDistance(hero.getPos()) == 0){
                m.attack(hero);
                hero.attack(m);
            }
        }
    }

    private void heroAttack(){
        Point p = hero.getPos();
        for (Monster m : monsterList){
            Point pm = m.getPos();
            if(Math.abs(pm.getX()-p.getX())<=1 && Math.abs(pm.getY()-p.getY())<=1){
                hero.attack(m);
            }
        }
    }

    private void checkIfWon() {
        int x = (int) hero.getPos().getX();
        int y = (int) hero.getPos().getY();

        gameOver = map.getCell(x, y).isChest();
    }

    public List<Monster> getMonsterList() {
        return monsterList;
    }

    @Override
    public void evolve(long dt) {
        moveMonsters(dt);
        hero.evolve(map, dt);
        monsterAttack();
        applyCellEffectOnPerson();
        removeDeadMonsters();
        checkIfWon();
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
            }case ATTACK: {
                heroAttack();
            }
            case IDLE: {
                hero.setSpeed(new Vector(0, 0));
            }
        }
        calcMonsterSpeeds();
    }

    @Override
    public boolean isFinished() {
        return hero.getLifepoints() <= 0 || gameOver;
    }
}
