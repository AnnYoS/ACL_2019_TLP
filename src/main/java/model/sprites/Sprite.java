package model.sprites;

import math.Vector;

import java.awt.image.BufferedImage;

public abstract class Sprite {

    protected BufferedImage sprite;

    protected int noanimx; //utilisé pour les animations
    protected int noaminy; //savoir l'orientation des monstres et du hero

    protected int lastanim;
    protected long timelastanim;
    protected static int freqanim = 100;

    public Sprite(BufferedImage img){
        sprite = img;
        noanimx = 0;
        lastanim = 0;
        timelastanim = 0;
        noaminy = 0;
    }

    public abstract BufferedImage getAnimation(Vector v, long dt);

    public BufferedImage getSprite() {
        return sprite;
    }
}
