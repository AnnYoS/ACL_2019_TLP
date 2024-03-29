package model.entity.person;

import math.Point;
import math.Vector;
import model.cell.Drawable;
import view.PacmanPainter;

import java.awt.image.BufferedImage;

public class Hero implements Person {
    public static final float SPEED = 0.005f;

    private Direction direction;
    private Point pos;
    private Vector acc;
    private Vector speed;
    private int lifepoints;
    private boolean isAttacking;

    public Hero(Point pos, int lp) {
        this.pos = pos;
        acc = new Vector(0, 0);
        this.lifepoints = lp;
        speed = new Vector(0, 0);
        direction = Direction.Down;
    }

    public void setSpeed(Vector speed) {
        if(this.speed.length() >= SPEED && Math.abs(acc.getX()) < 0.1 && Math.abs(acc.getY()) < 0.1) {
            if (!this.speed.equals(speed)) {
                this.speed = speed;
                acc = new Vector(0, 0);
            }
        }
        else if(this.speed.length() < SPEED * 0.8 && Math.abs(acc.getX()) < 0.04 && Math.abs(acc.getY()) < 0.04) {
            if (!this.speed.equals(speed)) {
                this.speed = speed;
                acc = new Vector(0, 0);
            }
        }

    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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

    public void setIsAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    public boolean isAttacking() {
        return isAttacking;
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
