package model.cell;

import model.person.Person;
import view.PacmanPainter;

import java.awt.image.BufferedImage;

public class Trap implements Cell {
    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public void applyDamage(Person p) {
        p.looseLP(1);
    }

    @Override
    public void draw(PacmanPainter p, BufferedImage img, int x, int y) {
        p.drawCell(img, this, x, y);
    }
}
