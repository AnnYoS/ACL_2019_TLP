package model.entity;

import math.Point;
import math.Vector;
import model.Map;

public class Projectile implements Entity {
    public static final float SPEED = 0.02f;

    private Point pos;
    private Vector speed;
    private Vector acc;

    private boolean keep = true;

    public Projectile(Point pos, Vector speed) {
        this.pos = pos;
        this.speed = speed;
        acc = new Vector(0, 0);
    }

    public Vector getDrawPos() {
        Vector v = acc.clone();
        v.add(pos);
        return v;
    }

    public void evolve(Map m, long dt) {
        if(acc.getY() > 0.5 || acc.getY() < -0.5 || acc.getX() < -0.5 || acc.getX() > 0.5) {
            Point next = null;
            if (speed.getX() > 0) {
                next = new Point(pos.getX() + 1, pos.getY());
            } else if (speed.getX() < 0) {
                next = new Point(pos.getX() - 1, pos.getY());
            } else if (speed.getY() > 0) {
                next = new Point(pos.getX(), pos.getY() + 1);
            } else if (speed.getY() < 0) {
                next = new Point(pos.getX(), pos.getY() - 1);
            }

            if(next != null) {
                if(! m.isWalkable(next)) {
                    speed = new Vector(0, 0);
                    keep = false;
                }
            }
        }
        acc.add(speed, dt);

        if(acc.getX() <= -1) {
            pos.setX(pos.getX() - 1);
            acc.setX(acc.getX() + 1);
        }
        else if(acc.getX() >= 1) {
            pos.setX(pos.getX() + 1);
            acc.setX(acc.getX() - 1);
        }
        else if(acc.getY() <= -1) {
            pos.setY(pos.getY() - 1);
            acc.setY(acc.getY() + 1);
        }
        else if(acc.getY() >= 1) {
            pos.setY(pos.getY() + 1);
            acc.setY(acc.getY() - 1);
        }
    }

    @Override
    public boolean keep() {
        return keep;
    }
}
