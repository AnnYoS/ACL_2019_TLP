package model.person;

import model.Map;
import model.Vector;

public interface Person {
    void attack (Person p);
    void looseLP (int lp);

    Vector getSpeed();
    void setSpeed(Vector v);

    void forceSetSpeed(Vector v);

    Vector getPos();
    void setPos(Vector v);

    default void evolve(Map m, long dt) {
        Vector speed = getSpeed();
        Vector next = getPos().clone();

        if(speed.getX() > 0) {
            next.setX((int)next.getX() + 1);
        }
        else if(speed.getX() < 0) {
            next.setX((int)next.getX() - 1);
        }
        else if(speed.getY() > 0) {
            next.setY((int)next.getY() + 1);
        }
        else if(speed.getY() < 0) {
            next.setY((int)next.getY() - 1);
        }

        if(m.isWalkable(next)) {
            getPos().add(getSpeed(), dt);
        }
        else {
            forceSetSpeed(new Vector(0, 0));
        }
    }
}
