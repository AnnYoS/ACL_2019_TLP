package model;

import java.awt.image.BufferedImage;

public class Sprite {

    private BufferedImage sprite;

    public Sprite(BufferedImage img){
        sprite = img;
    }

    public BufferedImage getSprite() {
        return sprite;
    }
}
