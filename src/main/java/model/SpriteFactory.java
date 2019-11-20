package model;

import model.dao.SpriteDAO;

public class SpriteFactory implements ISpriteFactory{

    private Sprite grass;
    private Sprite wall;
    private Sprite chest;
    private Sprite trap;

    public SpriteFactory(SpriteDAO spriteDAO){
        wall = new Sprite(spriteDAO.getWall());
        grass = new Sprite(spriteDAO.getGrass());
        chest = new Sprite(spriteDAO.getChest());
        trap = new Sprite(spriteDAO.getTrap());
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

