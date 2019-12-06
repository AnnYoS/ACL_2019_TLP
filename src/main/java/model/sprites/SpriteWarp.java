package model.sprites;

import math.Vector;

import java.awt.image.BufferedImage;

public class SpriteWarp extends Sprite {

    public SpriteWarp(BufferedImage img) {
        super(img);
        freqanim = 50;
    }

    @Override
    public BufferedImage getAnimation(Vector v, long dt) {
        timelastanim += dt;
        if (timelastanim > freqanim){
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

    @Override
    public Sprite clone() {
        return new SpriteWarp(sprite);
    }
}
