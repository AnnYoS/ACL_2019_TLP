package model.sprites;

import math.Vector;

import java.awt.image.BufferedImage;

public class SpriteAttack extends Sprite {
    private boolean finished = false;

    public SpriteAttack(BufferedImage img) {
        super(img);
    }

    @Override
    public BufferedImage getAnimation(Vector v, long dt) {
        timelastanim += dt;
        if (timelastanim > freqanim){
            if(noanimx >= 4){
                noanimx = 0;
                finished = true;
            } else {
                noanimx += 1;
            }
            timelastanim = 0;
        }
        return sprite.getSubimage(noanimx * 96, noanimy * 96, 96, 96);
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public Sprite clone() {
        return new SpriteAttack(sprite);
    }
}
