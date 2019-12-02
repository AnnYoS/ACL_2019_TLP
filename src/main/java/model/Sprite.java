package model;

import math.Vector;

import java.awt.image.BufferedImage;

public class Sprite {

    private BufferedImage sprite;

    private int noanim; //utilisé pour les animations
    private int lastanim;
    private long timelastanim;
    private static int freqanim = 150;
    private int currentdirection; //savoir l'orientation des monstres et du hero

    public Sprite(BufferedImage img){
        sprite = img;
        noanim = 0;
        lastanim = 0;
        timelastanim = 0;
        currentdirection = 0;
    }

    public BufferedImage getAnimation(Vector v){
        if(v.getX() != 0 || v.getY() != 0) {
            if (v.getX() < 0) {
                //vers la gauche
                currentdirection = 1;
            }
            if (v.getX() > 0) {
                //vers la droite
                currentdirection = 2;
            }
            if (v.getY() > 0) {
                //vers le bas
                currentdirection = 0;
            }
            if (v.getY() < 0) {
                //vers le haut
                currentdirection = 3;
            }

            if (System.currentTimeMillis() - timelastanim > freqanim){
                if (noanim == 0 || noanim == 2) {
                    lastanim = noanim;
                    noanim = 1;
                } else if (noanim == 1) {
                    if (lastanim == 0) {
                        noanim = 2;
                    } else {
                        noanim = 0;
                    }
                    lastanim = 1;
                }
                timelastanim = System.currentTimeMillis();
            }
        }
        return sprite.getSubimage(noanim * 32, currentdirection * 32, 32, 32);
    }

    public BufferedImage getSprite() {
        return sprite;
    }
}
