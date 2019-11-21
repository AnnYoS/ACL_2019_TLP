import engine.GameEngineGraphical;
import model.SpriteFactory;
import model.World;
import model.dao.DAOFactory;
import model.dao.SpriteDAO;
import view.PacmanController;
import view.PacmanPainter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //World game = new World();

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
        try {
            engine.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
