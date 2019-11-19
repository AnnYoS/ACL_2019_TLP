package model.person;

import model.Map;
import model.Vector;

public interface Person {
    void attack (Person p);
    void looseLP (int lp);

    Vector getSpeed();
    void setSpeed(Vector v);

    Vector getPos();
    void setPos(Vector v);

    default void evolve(Map m, long dt) {
        Vector old = getPos().clone();
        getPos().add(getSpeed(), dt);

        if(! m.isWalkable(getPos())) {
            setPos(old);
        }
    }
}
