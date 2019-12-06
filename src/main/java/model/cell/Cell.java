package model.cell;

import math.Point;
import model.person.Person;

import java.util.List;

public interface Cell extends Drawable {
    public boolean isWalkable();

    default void applyEffect(Person p) {

    }

    default boolean isChest() {
        return false;
    }
}
