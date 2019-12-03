package model.sprites;

import math.Vector;

import java.awt.image.BufferedImage;

public class SpriteStatic extends Sprite {

    public SpriteStatic(BufferedImage img){
        super(img);
    }

    @Override
    public BufferedImage getAnimation(Vector v, long dt) {
        //no use
        return sprite;
    }
}
