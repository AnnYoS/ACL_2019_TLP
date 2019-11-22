package model.person;

import math.Point;
import math.Vector;

public class Hero implements Person {
    public static final float SPEED = 0.005f;

    private Point pos;
    private Vector acc;
    private Vector speed;
    private int lifepoints;

    public Hero(Point pos, int lp) {
        this.pos = pos;
        acc = new Vector(0, 0);
        this.lifepoints = lp;
        speed = new Vector(0, 0);
    }

    public void setSpeed(Vector speed) {
        if(Math.abs(acc.getX()) <= 0.5 && Math.abs(acc.getY()) <= 0.5) {
            if (!this.speed.equals(speed)) {
                this.speed = speed;
                acc = new Vector(0, 0);
            }
        }
    }

    @Override
    public void forceSetSpeed(Vector v) {
        this.speed = v;
        acc = new Vector(0, 0);
    }

    @Override
    public void attack(Person p) {
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

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
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

    @Override
    public int getLifepoints() { return lifepoints; }
}
