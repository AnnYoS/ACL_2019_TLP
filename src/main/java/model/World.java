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

        int x = p.getX();
        int y = p.getY();

        map.getCell(x, y).applyEffect(hero);

        map.toggleWarps(hero.getPos());


        for(Monster m : monsterList) {
            x = m.getPos().getX();
            y = m.getPos().getY();

            map.getCell(x, y).applyEffect(m);
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
        for (Monster m : monsterList) {
            if (m.getPos().manhattanDistance(hero.getPos()) == 0) {
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
        hero.setIsAttacking(true);
    }

    private void checkIfWon() {
        int x = hero.getPos().getX();
        int y = hero.getPos().getY();

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
                hero.setSpeed(new Vector(0, -Hero.SPEED));
                break;
            }
            case DOWN: {
                hero.setSpeed(new Vector(0, Hero.SPEED));
                break;
            }case LEFT: {
                hero.setSpeed(new Vector(-Hero.SPEED, 0));
                break;
            }case RIGHT: {
                hero.setSpeed(new Vector(Hero.SPEED, 0));
                break;
            }case ATTACK: {
                heroAttack();
                break;
            }
            case IDLE: {
                hero.setSpeed(new Vector(0, 0));
                hero.setIsAttacking(false);
                break;
            }
        }
        calcMonsterSpeeds();
    }

    @Override
    public boolean isFinished() {
        return hero.getLifepoints() <= 0 || gameOver;
    }
}
