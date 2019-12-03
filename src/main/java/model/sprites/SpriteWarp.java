package model.sprites;

import math.Vector;

import java.awt.image.BufferedImage;

public class SpriteWarp extends Sprite {

    public SpriteWarp(BufferedImage img) {
        super(img);
    }

    @Override
    public BufferedImage getAnimation(Vector v, long dt) {
        timelastanim += dt;
        if (timelastanim > freqanim * 2.5){
            if(noanimx >= 4){
                noanimy += 1;
                noanimx = 0;
                if(noanimy > 2){
                    noanimy = 0;
                }
            } else {
                noanimx += 1;
            }
            timelastanim = 0;
        }
        return sprite.getSubimage(noanimx * 32, noanimy * 32, 32, 32);
    }
}
