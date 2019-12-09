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
    private Sprite attack;
    private Sprite walldestruc;

    public SpriteFactory(){

        BufferedImage bGrass = null;
        BufferedImage bWall = null;
        BufferedImage bChest = null;
        BufferedImage bTrap = null;
        BufferedImage bHero = null;
        BufferedImage bMonster = null;
        BufferedImage bLife = null;
        BufferedImage bWarp = null;
        BufferedImage bAttack = null;
        BufferedImage bWalldestruc = null;
        try {
            bGrass = ImageIO.read(new File("assets/grass32x32.png"));
            bWall = ImageIO.read(new File("assets/wall32x32.png"));
            bChest = ImageIO.read(new File("assets/chest32x32.png"));
            bTrap = ImageIO.read(new File("assets/trap32x32.png"));
            bHero = ImageIO.read(new File("assets/hero32x32.png"));
            bMonster = ImageIO.read(new File("assets/enemy32x32.png"));
            bLife = ImageIO.read(new File("assets/life32x32.png"));
            bWarp = ImageIO.read(new File("assets/warp32x32.png"));
            bAttack = ImageIO.read(new File("assets/slash_attack.png"));
            bWalldestruc = ImageIO.read(new File("assets/walldestructible32x32.png"));
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
        attack = new SpriteAttack(bAttack);
        walldestruc = new SpriteStatic(bWalldestruc);
    }

    public Sprite getGrass() {
        return grass.clone();
    }

    public Sprite getWall() {
        return wall.clone();
    }

    public Sprite getChest() {
        return chest.clone();
    }

    public Sprite getTrap() {
        return trap.clone();
    }

    public Sprite getHero() {
        return hero.clone();
    }

    public Sprite getEnemy() {
        return enemy.clone();
    }

    public Sprite getLife() { return life.clone(); }

    public Sprite getWarp() { return warp.clone(); }

    public Sprite getAttack() { return attack.clone(); }

    public Sprite getWalldestruc() {
        return walldestruc;
    }
}

