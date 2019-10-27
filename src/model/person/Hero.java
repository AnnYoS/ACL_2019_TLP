package model.person;

import model.Point;

public class Hero implements Person {
    private Point pos;

    public Hero(Point pos) {
        this.pos = pos;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }
}
