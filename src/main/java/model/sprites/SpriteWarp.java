package model.sprites;

import math.Vector;

import java.awt.image.BufferedImage;

public class SpriteWarp extends Sprite {

    public SpriteWarp(BufferedImage img) {
        super(img);
    }

    @Override
    public BufferedImage getAnimation(Vector v, long dt) {
        if (dt - timelastanim > freqanim){

            

            timelastanim = dt;
        }
        return sprite;
    }
}
