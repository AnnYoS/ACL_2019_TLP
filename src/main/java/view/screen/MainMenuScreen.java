package view.screen;

import engine.GameEngineGraphical;
import model.SpriteFactory;
import model.World;
import model.dao.DAOFactory;
import model.dao.SpriteDAO;
import view.PacmanController;
import view.PacmanPainter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public class MainMenuScreen implements Screen {
    private JPanel panel;

    private JList<String> levelList;

    private Screen nextScreen = this;
    private boolean keepScreen = true;

    public MainMenuScreen() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 600));

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JButton select = new JButton("Select");
        JButton exit = new JButton("Exit");

        panel.add(select);
        panel.add(exit);

        levelList = new JList<String>();

        levelList.setListData(getLevelList());

        panel.add(levelList);

        panel.setVisible(true);

        exit.addActionListener(event -> {
            synchronized (this) {
                keepScreen = false;
                nextScreen = null;
            }
        });

        select.addActionListener(event -> {
            String lvl = levelList.getSelectedValue();

            if(lvl != null) {
                String path = "levels/" + lvl + ".map";

                World game = null;
                try {
                    game = DAOFactory.getInstance().getWorldDAO().load(path);
                    //System.out.println("succ");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                SpriteFactory factory = SpriteDAO.getInstance().load();
                PacmanPainter painter = new PacmanPainter(game, factory);
                PacmanController controller = new PacmanController();

                // classe qui lance le moteur de jeu generique
                GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);


                keepScreen = true;
                nextScreen = engine;
            }
        });
    }

    private static String[] getLevelList() {
        Collection<String> levels = new ArrayList<>();

        try {
            Stream<Path> paths = Files.walk(Paths.get("levels/"));
            paths.filter(Files::isRegularFile)
                    .forEach(e -> {
                        String str = e.toString().substring("levels/".length());
                        if(str.endsWith(".map")) {
                            String name = str.replace(".map", "");
                            levels.add(name);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] res = new String[levels.size()];

        int i = 0;
        for(String s : levels) {
            res[i++] = s;
        }
        return res;
    }

    @Override
    public JPanel getContentPanel() {
        return panel;
    }

    @Override
    public void start() {
        nextScreen = this;
        keepScreen = true;
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
    public synchronized Screen getNextScreen() {
        return nextScreen;
    }
}
