package model.entity.person;

import math.Point;
import model.Map;
import math.Vector;
import model.entity.Entity;

public interface Person extends Entity {
    void attack (Person p);
    void looseLP (int lp);
    int getLifepoints();

    Vector getSpeed();
    void setSpeed(Vector v);

    void forceSetSpeed(Vector v);

    Point getPos();
    void setPos(Point v);

    Vector getAcc();
    void setAcc(Vector v);

    default void evolve(Map m, long dt) {
        Vector speed = getSpeed();
        Vector acc = getAcc();
        Point next = getPos().clone();

        if(speed.getX() < 0) {
            next.setX(next.getX() - 1);
        }
        else if(speed.getX() > 0) {
            next.setX(next.getX() + 1);
        }
        else if(speed.getY() < 0) {
            next.setY(next.getY() - 1);
        }
        else if(speed.getY() > 0) {
            next.setY(next.getY() + 1);
        }

        if(m.isWalkable(next)) {
            acc.add(speed, dt);

            if(acc.getX() >= 1) {
                setPos(next);

                acc.setX(acc.getX() - 1);
            }
            else if(acc.getX() <= -1) {
                setPos(next);

                acc.setX(acc.getX() + 1);
            }
            else if(acc.getY() >= 1) {
                setPos(next);

                acc.setY(acc.getY() - 1);
            }
            else if(acc.getY() <= -1) {
                setPos(next);

                acc.setY(acc.getY() + 1);
            }
        }
        else {
            forceSetSpeed(new Vector(0, 0));
        }
    }
}
