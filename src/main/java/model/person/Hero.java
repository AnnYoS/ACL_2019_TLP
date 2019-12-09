package model.person;

import math.Point;
import math.Vector;
import model.cell.Drawable;
import view.PacmanPainter;

import java.awt.image.BufferedImage;

public class Hero implements Person {
    public static final float SPEED = 0.005f;

    private Point pos;
    private Vector acc;
    private Vector speed;
    private int lifepoints;

    private boolean isAttacking;
    private Attack attack;

    public Hero(Point pos, int lp) {
        this.pos = pos;
        acc = new Vector(0, 0);
        this.lifepoints = lp;
        speed = new Vector(0, 0);
        this.attack = new Attack();
    }

    public void setSpeed(Vector speed) {
        if(Math.abs(acc.getX()) <= 0.1 && Math.abs(acc.getY()) <= 0.1) {
            if (!this.speed.equals(speed)) {
                this.speed = speed;
                acc = new Vector(0, 0);
            }
        }
    }

    public class Attack implements Drawable {
        @Override
        public void draw(PacmanPainter p, BufferedImage img, int x, int y) {
            //p.drawAttack(img, this, x, y);
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

    public void setIsAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public Attack getAttack() {
        return attack;
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
