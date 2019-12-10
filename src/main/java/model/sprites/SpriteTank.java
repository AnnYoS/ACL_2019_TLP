package model.sprites;

import math.Vector;

import java.awt.image.BufferedImage;

public class SpriteTank extends Sprite {

    public SpriteTank(BufferedImage img) {
        super(img);
    }

    @Override
    public BufferedImage getAnimation(Vector v, long dt) {
        timelastanim += dt;
        if(v.getX() != 0 || v.getY() != 0) {
            if (v.getX() < 0) {
                //vers la gauche
                noanimx = 2;
            }
            if (v.getX() > 0) {
                //vers la droite
                noanimx = 1;
            }
            if (v.getY() > 0) {
                //vers le bas
                noanimx = 3;
            }
            if (v.getY() < 0) {
                //vers le haut
                noanimx = 0;
            }
        }
        return sprite.getSubimage(noanimx * 32, noanimy * 32, 32, 32);
    }


    @Override
    public Sprite clone() {
        return this;
    }
}


