package model.cell;

import view.PacmanPainter;

import java.awt.image.BufferedImage;

public class Wall implements Cell {
    private boolean destructible;

    public Wall(boolean destructible) {
        this.destructible = destructible;
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public void draw(PacmanPainter p, BufferedImage img, int x, int y) {
        p.drawCell(img, this, x, y);
    }

    @Override
    public boolean isDestructible() {
        return destructible;
    }
}
