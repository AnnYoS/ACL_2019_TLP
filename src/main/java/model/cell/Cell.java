package model.cell;

import model.person.Person;

public interface Cell extends Drawable {
    public boolean isWalkable();

    default void applyDamage(Person p) {

    }
}
