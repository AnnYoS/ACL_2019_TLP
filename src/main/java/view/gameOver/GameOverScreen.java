package view.gameOver;

import view.Screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameOverScreen implements Screen {
    private JPanel panel;

    public GameOverScreen() {
        panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        try {
            BufferedImage img = ImageIO.read(new File("assets/game_over.png"));
            JLabel picLabel = new JLabel(new ImageIcon(img));
            panel.add(picLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JPanel getContentPanel() {
        return panel;
    }

    @Override
    public void start() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void step() {
        //System.out.println("step");
        //panel.repaint();
    }

    @Override
    public boolean keepScreen() {
        return true;
    }

    @Override
    public Screen getNextScreen() {
        return this;
    }
}
