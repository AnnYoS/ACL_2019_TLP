package view.screen;

import engine.GameEngineGraphical;
import model.sprites.SpriteFactory;
import model.World;
import model.dao.DAOFactory;
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
    private JPanel contentPanel;

    private JList<String> levelList;

    private Screen nextScreen = this;
    private boolean keepScreen = true;

    public MainMenuScreen() {
        //Liste des niveaux
        levelList = new JList<String>();

        levelList.setListData(getLevelList());

        JScrollPane listScroller = new JScrollPane(levelList);
        listScroller.setPreferredSize(new Dimension(250, 80));

        //Panneau de la liste des niveaux
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
        JLabel listLabel = new JLabel("Levels :");

        listPanel.add(listLabel);
        listPanel.add(Box.createRigidArea(new Dimension(0,5)));
        listPanel.add(listScroller);
        listPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        //JPanel pour les boutons
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        JButton select = new JButton("Select");
        buttonPane.add(select);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        JButton exit = new JButton("Exit");
        buttonPane.add(exit);

        //Container

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        contentPanel.setPreferredSize(new Dimension(400, 400));
        contentPanel.add(listPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPane, BorderLayout.PAGE_END);


        contentPanel.setVisible(true);

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

                SpriteFactory factory = new SpriteFactory();
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
        return contentPanel;
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
