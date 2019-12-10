package model.entity;

import math.Vector;
import model.Map;

public interface Entity {

    void evolve(Map m, long dt);
    Vector getDrawPos();

    default boolean keep() {
        return true;
    }
}
