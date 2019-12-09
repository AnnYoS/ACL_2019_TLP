package model.cell;

import math.Vector;
import model.entity.person.Person;
import view.PacmanPainter;

import java.awt.image.BufferedImage;

public class Sand implements Cell{
    private int ralenti = 0;

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public void applyEffect(Person p) {
        if(ralenti != 10){
            p.setSpeed(new Vector(0f,0f));
            ralenti++;
        }else{
            ralenti = 0;
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
