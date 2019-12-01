package view;

import engine.GameEngineGraphical;
import model.SpriteFactory;
import model.World;
import model.dao.DAOFactory;
import model.dao.SpriteDAO;

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

        World game = null;
        try {
            game = DAOFactory.getInstance().getWorldDAO().load("levels/lvl1.map");
        } catch (IOException e) {
            e.printStackTrace();
        }

        SpriteFactory factory = SpriteDAO.getInstance().load();
        PacmanPainter painter = new PacmanPainter(game, factory);
        PacmanController controller = new PacmanController();

        // classe qui lance le moteur de jeu generique
        GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
        screenStack.add(engine);

        // attacher le panel contenant l'afficheur du game
        /*this.panel=new DrawingPanel(gamePainter);
        f.setContentPane(this.panel);

        // attacher controller au panel du game
        this.panel.addKeyListener(gameController);*/

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

    private void timerStep() {
        Screen sc = getCurrentScreen();
        sc.step();
    }
}
