package model.cell;

import model.person.Person;

public interface Cell extends Drawable {
    boolean isWalkable();

    default void applyEffect(Person p) {

    }

    default boolean isChest() {
        return false;
    }

    default boolean isDestructible() {
        return false;
    }
}
