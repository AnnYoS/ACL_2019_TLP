package view;

import java.awt.*;
import java.awt.image.BufferedImage;

import engine.Game;
import engine.GamePainter;
import model.*;
import model.cell.*;
import model.dao.SpriteDAO;
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

	private static final int BLOCK_SIZE = 32;

	protected static final int WIDTH = BLOCK_SIZE * 32;
	protected static final int HEIGHT = BLOCK_SIZE * 32;

	private World game;

	private SpriteFactory spriteFactory;

	/**
	 * appelle constructeur parent
	 * 
	 * @param game
	 *            le jeutest a afficher
	 */
	public PacmanPainter(Game game) {
		this.game = (World) game;
		SpriteDAO spriteDAO = new SpriteDAO();
		spriteDAO.load();
		this.spriteFactory = new SpriteFactory(spriteDAO);
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();

		Map map = game.getMap();
		for(int i=0; i < map.getW();i++){
			for(int j=0; j < map.getH();j++){
				Cell c = map.getCell(i, j);
				c.draw(this, im, i, j);
			}
		}

		crayon.setColor(Color.blue);
		Vector heroPos = game.getHeroPos();
		crayon.fillOval(((int)heroPos.getX() * BLOCK_SIZE),((int)heroPos.getY() * BLOCK_SIZE),BLOCK_SIZE,BLOCK_SIZE);

		crayon.setColor(Color.red);
		for (Monster m: game.getMonsterList()){
			crayon.fillOval(((int)m.getPos().getX() * BLOCK_SIZE), ((int)m.getPos().getY() * BLOCK_SIZE), BLOCK_SIZE,BLOCK_SIZE);
		}
	}

	public void drawCell(BufferedImage img, Grass gr, int x, int y) {
		Graphics g = img.getGraphics();
		g.drawImage(spriteFactory.getGrass().getSprite(), x * BLOCK_SIZE, y * BLOCK_SIZE, null);
	}

	public void drawCell(BufferedImage img, Wall w, int x, int y) {
		Graphics g = img.getGraphics();
		g.drawImage(spriteFactory.getWall().getSprite(), x * BLOCK_SIZE, y * BLOCK_SIZE, null);
		//g.setColor(Color.GRAY);
		//g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
	}

	public void drawCell(BufferedImage img, Trap t, int x, int y) {
		Graphics g = img.getGraphics();
		g.drawImage(spriteFactory.getGrass().getSprite(), x * BLOCK_SIZE, y * BLOCK_SIZE, null);
		g.drawImage(spriteFactory.getTrap().getSprite(), x * BLOCK_SIZE, y * BLOCK_SIZE, null);
		//g.setColor(Color.CYAN);
		//g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
	}

	public void drawCell(BufferedImage img, Chest c, int x, int y) {
		Graphics g = img.getGraphics();
		g.drawImage(spriteFactory.getGrass().getSprite(), x * BLOCK_SIZE, y * BLOCK_SIZE, null);
		g.drawImage(spriteFactory.getChest().getSprite(), x * BLOCK_SIZE, y * BLOCK_SIZE, null);
		//g.setColor(Color.YELLOW);
		//g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
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
