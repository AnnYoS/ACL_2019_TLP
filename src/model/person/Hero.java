package model.person;

import model.Point;

public class Hero implements Person {
    private Point pos;
    private int lifepoints;

    public Hero(Point pos, int lp) {
        this.pos = pos;
        this.lifepoints = lp;
    }

    @Override
    public void attack(Person p) {
        p.looseLP(10);
    }

    @Override
    public void looseLP(int lp) {
        lifepoints = lifepoints - lp;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public int getLifepoints() { return lifepoints; }
}
