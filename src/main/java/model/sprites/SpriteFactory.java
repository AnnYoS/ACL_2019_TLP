package model.sprites;

import model.ISpriteFactory;
import model.sprites.Sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteFactory implements ISpriteFactory {

    private Sprite grass;
    private Sprite wall;
    private Sprite chest;
    private Sprite trap;
    private Sprite hero;
    private Sprite enemy;
    private Sprite life;
    private Sprite warp;

    public SpriteFactory(){

        BufferedImage bGrass = null;
        BufferedImage bWall = null;
        BufferedImage bChest = null;
        BufferedImage bTrap = null;
        BufferedImage bHero = null;
        BufferedImage bMonster = null;
        BufferedImage bLife = null;
        BufferedImage bWarp = null;
        try {
            bGrass = ImageIO.read(new File("assets/grass32x32.png"));
            bWall = ImageIO.read(new File("assets/wall32x32.png"));
            bChest = ImageIO.read(new File("assets/chest32x32.png"));
            bTrap = ImageIO.read(new File("assets/trap32x32.png"));
            bHero = ImageIO.read(new File("assets/hero32x32.png"));
            bMonster = ImageIO.read(new File("assets/enemy32x32.png"));
            bLife = ImageIO.read(new File("assets/life32x32.png"));
            bWarp = ImageIO.read(new File("assets/warp32x32.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        wall = new SpriteStatic(bWall);
        grass = new SpriteStatic(bGrass);
        chest = new SpriteStatic(bChest);
        trap = new SpriteStatic(bTrap);
        hero = new SpritePerson(bHero);
        enemy = new SpritePerson(bMonster);
        life = new SpriteStatic(bLife);
        warp = new SpriteWarp(bWarp);
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

    public Sprite getWarp() { return warp; }
}

