package view;

import engine.GameEngineGraphical;
import model.SpriteFactory;
import model.World;
import model.dao.DAOFactory;
import model.dao.SpriteDAO;
import view.screen.MainMenuScreen;
import view.screen.Screen;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainFrame {
    private JFrame frame;
    private List<Screen> screenStack;
    private Timer timer;

    public MainFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        screenStack = new ArrayList<>(5);

        Screen engine = new MainMenuScreen();
        screenStack.add(engine);

        frame.setContentPane(engine.getContentPanel());
        frame.getContentPane().requestFocus();

        frame.pack();
        frame.setVisible(true);
        frame.getContentPane().setFocusable(true);
        frame.getContentPane().requestFocus();

        timer = new Timer(1000 / 60, (e) -> {
            timerStep();
        });
        timer.start();
        engine.start();
    }

    private Screen getCurrentScreen() {
        return screenStack.get(screenStack.size() - 1);
    }

    private void popScreen() {
        screenStack.remove(screenStack.size() - 1);
    }

    private void timerStep() {
        Screen sc = getCurrentScreen();
        sc.step();

        if (sc.getNextScreen() != sc) {
            Screen next = sc.getNextScreen();
            sc.pause();

            if(! sc.keepScreen()) {
                popScreen();
            }

            if(screenStack.size() == 0) {
                System.exit(0);
            }

            if(next == null) {
                next = getCurrentScreen();
            }
            else {
                screenStack.add(next);
            }

            frame.setContentPane(next.getContentPanel());
            frame.getContentPane().setFocusable(true);
            frame.getContentPane().requestFocus();
            next.start();
            frame.pack();
        }
    }
}
