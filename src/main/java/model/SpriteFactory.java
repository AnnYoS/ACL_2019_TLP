package model;

import model.dao.SpriteDAO;

import java.awt.image.BufferedImage;

public class SpriteFactory implements ISpriteFactory{

    private Sprite grass;
    private Sprite wall;
    private Sprite chest;
    private Sprite trap;
    private Sprite hero;
    private Sprite enemy;
    private Sprite life;

    public SpriteFactory(BufferedImage... sprites){
        wall = new Sprite(sprites[1]);
        grass = new Sprite(sprites[0]);
        chest = new Sprite(sprites[2]);
        trap = new Sprite(sprites[3]);
        hero = new Sprite(sprites[4]);
        enemy = new Sprite(sprites[5]);
        life = new Sprite(sprites[6]);
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

    public Sprite getHero() {
        return hero;
    }

    public Sprite getEnemy() {
        return enemy;
    }

    public Sprite getLife() { return life; }
}

