package model;

import model.dao.SpriteDAO;

import java.awt.image.BufferedImage;

public class SpriteFactory implements ISpriteFactory{

    private Sprite grass;
    private Sprite wall;
    private Sprite chest;
    private Sprite trap;

    public SpriteFactory(BufferedImage... sprites){
        wall = new Sprite(sprites[1]);
        grass = new Sprite(sprites[0]);
        chest = new Sprite(sprites[2]);
        trap = new Sprite(sprites[3]);
    }

    public Sprite getGrass() {
        return grass;
    }

    public Sprite getWall() {
        return wall;
    }

    public Sprite getChest() {
        return chest;
    }

    public Sprite getTrap() {
        return trap;
    }
}

