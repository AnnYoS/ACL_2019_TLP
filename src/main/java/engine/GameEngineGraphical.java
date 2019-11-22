package engine;

import view.GameOverController;
import view.GameOverPainter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * moteur de game generique.
 * On lui passe un game et un afficheur et il permet d'executer un game.
 */
public class GameEngineGraphical {

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
	}

	/**
	 * permet de lancer le game
	 */
	public void run() throws InterruptedException {

		// creation de l'interface graphique
		this.gui = new GraphicalInterface(this.gamePainter,this.gameController);

		long time = System.currentTimeMillis();
		long max_time = 1000 / 10;

		// boucle de game
		while (!this.game.isFinished()) {
			// demande controle utilisateur
			Cmd c = this.gameController.getCommand();
			// fait evoluer le game
			this.game.events(c);

			long dt = System.currentTimeMillis() - time;
			time = System.currentTimeMillis();
			System.out.println(1000 / dt);

			this.game.evolve(dt);
			// affiche le game
			this.gui.paint();
			// met en attente
			Thread.sleep(Math.max(0, max_time - dt));
		}
		try {
			GameOverController controller = new GameOverController();
			GraphicalInterface gameOver = new GraphicalInterface(new GameOverPainter(ImageIO.read(new File("assets/game_over.png"))), controller);
			this.gui.dispose();
			boolean exit = false;
			while (!exit) {
				Cmd commande = controller.getCommand();
				switch (commande) {
					case YES: {
						System.out.println("Retry");
						break;
					}
					case NO: {
						exit = true;
						break;
					}
					default:{}
				}
				gameOver.paint();
				Thread.sleep(100);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.exit(0);
	}

}
