package model;

import engine.Cmd;
import engine.Game;
import math.Point;
import math.Vector;
import model.cell.Cell;
import model.cell.Grass;
import model.entity.Entity;
import model.entity.Projectile;
import model.entity.person.Direction;
import model.entity.person.Hero;
import model.entity.person.Monster;

import java.util.ArrayList;
import java.util.List;

public class World implements Game {
    private Map map;
    private Hero hero;
    private boolean gameOver = false;
    private List<Monster> monsterList;

    private List<Entity> projectiles;
    private long shootDt = 0;
    private static final long SHOOT_INTERVAL = 250;

    public World() {
        projectiles = new ArrayList<>(5);
    }

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

        for(int j = -1; j < 2; j++) {
            for(int i = -1; i < 2; i++) {
                int x = i + p.getX();
                int y = j + p.getY();

                if(x >= 0 && y >= 0 && x < map.getW() && y < map.getH()) {
                    Cell c = map.getCell(x, y);

                    if (c.isDestructible()) {
                        map.setCell(x, y, new Grass());
                    }
                }
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

    public List<Entity> getProjectiles() {
        return projectiles;
    }

    private void moveProjectiles(long dt) {
        for(int i = projectiles.size() - 1; i >= 0; i--) {
            Entity e = projectiles.get(i);

            e.evolve(map, dt);

            if(!e.keep()) {
                projectiles.remove(i);
            }
        }
    }

    private void projectileMonsterCollisions() {
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Entity e = projectiles.get(i);
            Vector ep = e.getDrawPos();
            ep.add(new Vector(0.5f, 0.5f));

            for (int j = monsterList.size() - 1; j >= 0; j--) {
                Monster m = monsterList.get(j);
                Vector mp = m.getDrawPos();

                mp.add(new Vector(0.5f, 0.5f));

                if(mp.distance(ep) <= 0.3) {
                    m.looseLP(1);

                    projectiles.remove(i);

                    if(m.getLifepoints() <= 0) {
                        monsterList.remove(j);
                    }

                    break;
                }
            }
        }
    }

    @Override
    public void evolve(long dt) {
        applyCellEffectOnPerson();
        hero.evolve(map, dt);

        moveMonsters(dt);
        moveProjectiles(dt);

        monsterAttack();
        projectileMonsterCollisions();

        removeDeadMonsters();
        checkIfWon();

        shootDt -= dt;
        if(shootDt < 0) {
            shootDt = 0;
        }
    }

    @Override
    public void events(Cmd c) {
        switch (c) {
            case UP: {
                hero.setSpeed(new Vector(0, -Hero.SPEED));
                hero.setDirection(Direction.Up);
                break;
            }
            case DOWN: {
                hero.setSpeed(new Vector(0, Hero.SPEED));
                hero.setDirection(Direction.Down);
                break;
            }case LEFT: {
                hero.setSpeed(new Vector(-Hero.SPEED, 0));
                hero.setDirection(Direction.Left);
                break;
            }case RIGHT: {
                hero.setSpeed(new Vector(Hero.SPEED, 0));
                hero.setDirection(Direction.Right);
                break;
            }case ATTACK: {
                hero.setSpeed(new Vector(0, 0));
                heroAttack();
                break;
            }
            case SHOOT: {
                hero.setSpeed(new Vector(0, 0));
                if(shootDt <= 0) {
                    Vector tmp = null;
                    switch (hero.getDirection()) {
                        case Up:
                            tmp = new Vector(0, -Projectile.SPEED);
                            break;
                        case Down:
                            tmp = new Vector(0, Projectile.SPEED);
                            break;
                        case Left:
                            tmp = new Vector(-Projectile.SPEED, 0);
                            break;
                        case Right:
                            tmp = new Vector(Projectile.SPEED, 0);
                            break;
                    }
                    projectiles.add(new Projectile(hero.getPos().clone(), tmp));
                    shootDt = SHOOT_INTERVAL;
                }
                break;
            }
            case IDLE: {
                hero.setSpeed(new Vector(0, 0));
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
