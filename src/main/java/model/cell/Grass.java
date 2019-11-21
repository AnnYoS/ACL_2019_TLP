package model.cell;

import view.PacmanPainter;

import java.awt.image.BufferedImage;

public class Grass implements Cell{
    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public void draw(PacmanPainter p, BufferedImage img, int x, int y) {
        p.drawCell(img, this, x, y);
    }
}
