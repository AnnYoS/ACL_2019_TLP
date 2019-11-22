package model.person;

import math.Point;
import model.Map;
import math.Vector;
import model.person.strategy.MonsterStrategy;

public class Monster implements Person {

    public static float SPEED = 0.0025f;

    private Point pos;
    private Vector acc;
    private Vector speed;
    private int lifepoints;
    private MonsterStrategy moveStrategy;

    public Monster(Point pos, MonsterStrategy moveStrategy, int lp) {
        this.pos = pos;
        acc = new Vector(0, 0);
        this.moveStrategy = moveStrategy;
        this.lifepoints = lp;
        speed = new Vector(0, 0);
    }

    @Override
    public void attack(Person p){
        p.looseLP(1);
    }

    @Override
    public void looseLP(int lp) {
        lifepoints = lifepoints - lp;
    }

    @Override
    public Vector getSpeed() {
        return speed;
    }

    public void setSpeed(Vector speed) {
        if(!this.speed.equals(speed)) {
            this.speed = speed;
            acc = new Vector(0, 0);
        }
    }

    @Override
    public void forceSetSpeed(Vector v) {
        this.speed = v;
        acc = new Vector(0, 0);
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public void calcSpeed(Map map, Point heroPos) {
        Vector tmp =  moveStrategy.move(this, map, heroPos);
        setSpeed(tmp);
    }

    @Override
    public Vector getAcc() {
        return acc;
    }

    @Override
    public void setAcc(Vector v) {
        acc = v;
    }

    @Override
    public Vector getDrawPos() {
        Vector tmp = acc.clone();
        tmp.add(pos);
        return tmp;
    }

    public int getLifepoints() { return lifepoints; }
}
