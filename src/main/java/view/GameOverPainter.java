package view;

import engine.GamePainter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameOverPainter implements GamePainter {

    Image gameOver;

    private static final int BLOCK_SIZE = 32;

    protected static final int WIDTH = BLOCK_SIZE * 20;
    protected static final int HEIGHT = BLOCK_SIZE * 20;

    public GameOverPainter(Image gameOver) throws IOException {
        this.gameOver = gameOver.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);
    }

    @Override
    public void draw(BufferedImage image) {
        Graphics2D crayon = (Graphics2D) image.getGraphics();
        crayon.drawImage(this.gameOver,0, 0, null);
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
