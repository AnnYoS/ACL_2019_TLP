package model;

import math.Vector;

import java.awt.image.BufferedImage;

public class Sprite {

    private BufferedImage sprite;
    private int noanim; //utilisé pour les animations
    private int currentdirection; //savoir l'orientation des monstres et du hero

    public Sprite(BufferedImage img){
        sprite = img;
        noanim = 0;
        currentdirection = 0;
    }

    public BufferedImage getAnimation(Vector v){
        if(v.getX() < 0){
            //vers la gauche
            currentdirection = 1;
            noanim += 1;
        }
        if(v.getX() > 0){
            //vers la droite
            currentdirection = 2;
            noanim += 1;
        }
        if(v.getY() > 0){
            //vers le bas
            currentdirection = 0;
            noanim += 1;
        }
        if(v.getY() < 0){
            //vers le haut
            currentdirection = 3;
            noanim += 1;
        }

        if(noanim > 2) {
            noanim = 0;
        }
        return sprite.getSubimage(noanim * 32, currentdirection * 32, 32, 32);
    }

    public BufferedImage getSprite() {
        return sprite;
    }
}
