package model.sprites;

import math.Vector;

import java.awt.image.BufferedImage;

public class SpritePerson extends Sprite{

    public SpritePerson(BufferedImage img){
        super(img);
    }

    @Override
    public BufferedImage getAnimation(Vector v, long dt) {
        timelastanim += dt;
        if(v.getX() != 0 || v.getY() != 0) {
            if (v.getX() < 0) {
                //vers la gauche
                noanimy = 1;
            }
            if (v.getX() > 0) {
                //vers la droite
                noanimy = 2;
            }
            if (v.getY() > 0) {
                //vers le bas
                noanimy = 0;
            }
            if (v.getY() < 0) {
                //vers le haut
                noanimy = 3;
            }

            if (timelastanim > freqanim){
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
                timelastanim = 0;
            }
        }
        return sprite.getSubimage(noanimx * 32, noanimy * 32, 32, 32);
    }
}
