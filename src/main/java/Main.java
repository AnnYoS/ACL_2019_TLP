import view.*;

public class Main {
    public static void main(String[] args) {
        //World game = new World();

        /*World game = null;
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
        }*/
        new MainFrame();
    }
}
