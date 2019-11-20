package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteFactory implements ISpriteFactory{

    private static Sprite grass;
    private static Sprite wall;

    public SpriteFactory(){
        wall = new Sprite(new File("assets/wall32x32.png"));
        grass = new Sprite(new File("assets/grass32x32.png"));
    }
}
