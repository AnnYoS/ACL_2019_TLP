package model.sprites;

import math.Vector;

import java.awt.image.BufferedImage;

public class SpritePerson extends Sprite{

    public SpritePerson(BufferedImage img){
        super(img);
    }

    @Override
    public BufferedImage getAnimation(Vector v, long dt) {
        if(v.getX() != 0 || v.getY() != 0) {
            if (v.getX() < 0) {
                //vers la gauche
                noaminy = 1;
            }
            if (v.getX() > 0) {
                //vers la droite
                noaminy = 2;
            }
            if (v.getY() > 0) {
                //vers le bas
                noaminy = 0;
            }
            if (v.getY() < 0) {
                //vers le haut
                noaminy = 3;
            }

            if (dt - timelastanim > freqanim){
                if (noanimx == 0 || noanimx == 2) {
                    lastanim = noanimx;
                    noanimx = 1;
                } else if (noanimx == 1) {
                    if (lastanim == 0) {
                        noanimx = 2;
                    } else {
                        noanimx = 0;
                    }
                    lastanim = 1;
                }
                timelastanim = dt;
            }
        }
        return sprite.getSubimage(noanimx * 32, noaminy * 32, 32, 32);
    }
}
