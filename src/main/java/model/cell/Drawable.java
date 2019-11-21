package model.cell;

import view.PacmanPainter;

import java.awt.image.BufferedImage;

public interface Drawable {
    void draw(PacmanPainter p, BufferedImage img, int x, int y);
}
