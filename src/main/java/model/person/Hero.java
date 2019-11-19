package model.person;

import model.Vector;

public class Hero implements Person {
    private Vector pos;
    private int lifepoints;

    public Hero(Vector pos, int lp) {
        this.pos = pos;
        this.lifepoints = lp;
    }

    @Override
    public void attack(Person p) {
        p.looseLP(1);
    }

    @Override
    public void looseLP(int lp) {
        lifepoints = lifepoints - lp;
    }

    public Vector getPos() {
        return pos;
    }

    public void setPos(Vector pos) {
        this.pos = pos;
    }

    public int getLifepoints() { return lifepoints; }
}
