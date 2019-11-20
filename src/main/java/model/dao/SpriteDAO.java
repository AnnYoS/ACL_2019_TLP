package model.dao;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteDAO implements ISpriteDAO {

    private BufferedImage grass;
    private BufferedImage wall;
    private BufferedImage chest;
    private BufferedImage trap;

    @Override
    public void load() {
        try {
            grass = ImageIO.read(new File("assets/grass32x32.png"));
            wall = ImageIO.read(new File("assets/wall32x32.png"));
            chest = ImageIO.read(new File("assets/chest32x32.png"));
            trap = ImageIO.read(new File("assets/trap32x32.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getGrass() {
        return grass;
    }

    public BufferedImage getWall() {
        return wall;
    }

    public BufferedImage getChest() {
        return chest;
    }

    public BufferedImage getTrap() {
        return trap;
    }
}
