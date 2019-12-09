package model.cell;

import math.Vector;
import model.entity.person.Hero;
import model.entity.person.Person;
import view.PacmanPainter;

import java.awt.image.BufferedImage;

public class Sand implements Cell{

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public void applyEffect(Person p) {
        if(p.getClass().equals(Hero.class)) {
            Vector s = p.getAcc();
            Vector n = new Vector(s.getX() * 0.85f, s.getY() * 0.85f);
            p.setAcc(n);
        }
    }

    @Override
    public boolean isChest() {
        return false;
    }

    @Override
    public boolean isDestructible() {
        return false;
    }

    @Override
    public void draw(PacmanPainter p, BufferedImage img, int x, int y) {
        p.drawCell(img, this, x, y);
    }
}
