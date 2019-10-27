import engine.GameEngineGraphical;
import model.Point;
import model.World;
import view.PacmanController;
import view.PacmanPainter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        World game = new World();

        PacmanPainter painter = new PacmanPainter(game);
        PacmanController controller = new PacmanController();

        // classe qui lance le moteur de jeu generique
        GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
        try {
            engine.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
