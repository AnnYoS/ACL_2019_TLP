package engine;

import view.screen.GameOverScreen;
import view.screen.Screen;

import javax.swing.*;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * moteur de game generique.
 * On lui passe un game et un afficheur et il permet d'executer un game.
 */
public class GameEngineGraphical implements Screen {

	/**
	 * le game a executer
	 */
	private Game game;

	/**
	 * l'afficheur a utiliser pour le rendu
	 */
	private GamePainter gamePainter;

	/**
	 * le controlleur a utiliser pour recuperer les commandes
	 */
	private GameController gameController;

	/**
	 * l'interface graphique
	 */
	private GraphicalInterface gui;

	/**
	 * construit un moteur
	 * 
	 * @param game
	 *            game a lancer
	 * @param gamePainter
	 *            afficheur a utiliser
	 * @param gameController
	 *            controlleur a utiliser
	 *            
	 */
	public GameEngineGraphical(Game game, GamePainter gamePainter, GameController gameController) {
		// creation du game
		this.game = game;
		this.gamePainter = gamePainter;
		this.gameController = gameController;
		gui = new GraphicalInterface(this.gamePainter, this.gameController);
	}

	/**
	 * @deprecated
	 * permet de lancer le game
	 */
	public void run() throws InterruptedException {

		// creation de l'interface graphique
		this.gui = new GraphicalInterface(this.gamePainter,this.gameController);

		long time = System.currentTimeMillis();
		long max_time = 1000 / 100;
		long dt = 0;

		// boucle de game
		while (!this.game.isFinished()) {
			// demande controle utilisateur
			Cmd c = this.gameController.getCommand();
			// fait evoluer le game
			this.game.events(c);

			this.game.evolve(dt);
			// affiche le game
			this.gui.paint();
			// met en attente
			//Thread.sleep(Math.max(0, max_time - dt));
			Thread.sleep(10);

			dt = System.currentTimeMillis() - time;
			time = System.currentTimeMillis();
			System.out.println((1000f / dt));
		}
		System.exit(0);
	}

	private long time;
	private long dt;

	@Override
	public JPanel getContentPanel() {
		return gui.getPanel();
	}

	@Override
	public void start() {
		time = System.currentTimeMillis();
		dt = 0;
	}

	@Override
	public void pause() {
		start();
	}

	@Override
	public void step() {
		dt = System.currentTimeMillis() - time;
		time = System.currentTimeMillis();

		//System.out.println(1000.0 / dt);

		// demande controle utilisateur
		Cmd c = this.gameController.getCommand();
		// fait evoluer le game
		this.game.events(c);

		this.game.evolve(dt);
		// affiche le game
		this.gui.paint();
	}

	@Override
	public boolean keepScreen() {
		return ! game.isFinished();
	}

	@Override
	public Screen getNextScreen() {
		if(game.isFinished()) {
			return new GameOverScreen();
		}
		return this;
	}
}
