package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {

    private BufferedImage sprite;

    public Sprite(File f){
        try {
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(String.valueOf(f)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite() {
        return sprite;
    }
}
