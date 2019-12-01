package model.dao;

import model.SpriteFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class SpriteDAO implements ISpriteDAO {

    private static SpriteDAO instance = null;

    public static SpriteDAO getInstance(){
        if(instance == null){
            instance = new SpriteDAO();
        }
        return instance;
    }

    @Override
    public SpriteFactory load() {
        BufferedImage grass = null;
        BufferedImage wall = null;
        BufferedImage chest = null;
        BufferedImage trap = null;
        BufferedImage hero = null;
        BufferedImage monster = null;
        BufferedImage life = null;
        try {
            grass = ImageIO.read(new File("assets/grass32x32.png"));
            wall = ImageIO.read(new File("assets/wall32x32.png"));
            chest = ImageIO.read(new File("assets/chest32x32.png"));
            trap = ImageIO.read(new File("assets/trap32x32.png"));
            hero = ImageIO.read(new File("assets/hero32x32.png"));
            monster = ImageIO.read(new File("assets/enemy32x32.png"));
            life = ImageIO.read(new File("assets/life32x32.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new SpriteFactory(grass, wall, chest, trap, hero, monster, life);
    }
}
