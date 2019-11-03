package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import engine.Game;
import engine.GamePainter;
import model.Point;
import model.World;
import model.cell.Cell;
import model.cell.Wall;
import model.person.Monster;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class PacmanPainter implements GamePainter {

	/**
	 * la taille des cases
	 */

	private static final int BLOCK_SIZE = 20;

	protected static final int WIDTH = BLOCK_SIZE * 20;
	protected static final int HEIGHT = BLOCK_SIZE * 20;

	private World game;

	/**
	 * appelle constructeur parent
	 * 
	 * @param game
	 *            le jeutest a afficher
	 */
	public PacmanPainter(Game game) {
		this.game = (World) game;
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();

		Cell[][] mapCells = game.getMapCells();
		for(int i=0; i<mapCells.length;i++){
			for(int j=0; j<mapCells[0].length;j++){
				if(mapCells[i][j].getClass().equals(Wall.class)){
					crayon.setColor(Color.gray);
					crayon.fillRect(i * BLOCK_SIZE, j * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
				}
			}
		}

		crayon.setColor(Color.blue);
		Point heroPos = game.getHeroPos();
		crayon.fillOval(heroPos.getX() * BLOCK_SIZE,heroPos.getY() * BLOCK_SIZE,BLOCK_SIZE,BLOCK_SIZE);

		crayon.setColor(Color.red);
		for (Monster m: game.getMonsterList()){
			crayon.fillOval(m.getPos().getX() * BLOCK_SIZE, m.getPos().getY() * BLOCK_SIZE, BLOCK_SIZE,BLOCK_SIZE);
		}


	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

}
