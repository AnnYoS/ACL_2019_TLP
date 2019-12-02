package view.screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameOverScreen implements Screen {
    private JPanel panel;

    private boolean keepScreen = true;
    private Screen nextScreen = this;

    public GameOverScreen() {
        panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JButton back = new JButton("Back to Main Menu");
        back.setBounds(panel.getWidth(), panel.getHeight(), 150, 40);
        back.addActionListener(e -> {
            nextScreen = null;
            keepScreen = false;
        });

        try {
            BufferedImage img = ImageIO.read(new File("assets/game_over.png"));
            JLabel picLabel = new JLabel(new ImageIcon(img));
            picLabel.add(back);
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
    }

    @Override
    public boolean keepScreen() {
        return keepScreen;
    }

    @Override
    public Screen getNextScreen() {
        return nextScreen;
    }
}
