package engine;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         un jeu qui peut evoluer (avant de se terminer) sur un plateau width x
 *         height
 */
public interface Game {

	/**
	 * modifie certaines propriétés physiques du jeu en fonction de l'entrée utilisateur
	 * (modifie les vecteurs de vitesse)
	 * 
	 * @param c
	 *            commande utilisateur
	 */
	void events(Cmd c);

	/**
	 * fait evoluer la physique du jeu
	 * @param dt
	 */
	void evolve(long dt);

	/**
	 * @return true si et seulement si le jeu est fini
	 */
	boolean isFinished();
}
