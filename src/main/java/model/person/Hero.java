package model.person;

import model.Map;
import model.Vector;

public class Hero implements Person {
    public static final float SPEED = 0.005f;

    private Vector pos;
    private Vector speed;
    private int lifepoints;

    public Hero(Vector pos, int lp) {
        this.pos = pos;
        this.lifepoints = lp;
        speed = new Vector(0, 0);
    }

    public void setSpeed(Vector speed) {
        if(! speed.equals(this.speed)) {
            if(this.speed.getX() > 0) {
                pos.setX(pos.getXasInt());
            }
            else if(this.speed.getX() < 0) {
                pos.setX((int)pos.getX());
            }
            else if(this.speed.getY() > 0) {
                pos.setY((int)pos.getY());
            }
            else if(this.speed.getY() < 0) {
                pos.setY((int)pos.getY());
            }

            this.speed = speed;
        }
    }

    @Override
    public void forceSetSpeed(Vector v) {
        pos.setX(pos.getXasInt());
        pos.setY(pos.getYasInt());

        this.speed = v;
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

    public Vector getPos() {
        return pos;
    }

    public void setPos(Vector pos) {
        this.pos = pos;
    }

    public int getLifepoints() { return lifepoints; }
}
