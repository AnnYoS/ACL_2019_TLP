package model.cell;

import math.Point;
import math.Vector;
import model.person.Hero;
import model.person.Person;
import view.PacmanPainter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Warp implements Cell {
    private Point dest;
    private boolean activated = true;


    public void setDest(Point point){
        dest=point;
    }

    public Point getDest() {
        return dest;
    }

    public void desactivate(){
        activated=false;
    }

    public void activate(){
        activated=true;
    }


    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public void applyEffect(Person p) {
        if(dest!=null && p.getClass()== Hero.class && activated) {
            p.setPos(dest);
        }
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
