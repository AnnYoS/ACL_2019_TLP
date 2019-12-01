package model.cell;

import math.Point;
import math.Vector;
import model.person.Person;
import view.PacmanPainter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Warp implements Cell {
    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public void applyEffect(Person p) {

    }

    @Override
    public boolean isChest() {
        return false;
    }

    @Override
    public void draw(PacmanPainter p, BufferedImage img, int x, int y) {
        p.drawCell(img, this, x, y);
    }
}
