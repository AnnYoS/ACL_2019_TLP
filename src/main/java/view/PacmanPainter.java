package view;

import java.awt.*;
import java.awt.image.BufferedImage;

import engine.Game;
import engine.GamePainter;
import math.Vector;
import model.*;
import model.cell.*;
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

	protected static final int WIDTH = BLOCK_SIZE * 20;
	protected static final int HEIGHT = BLOCK_SIZE * 20;

	private World game;

	private SpriteFactory spriteFactory;

	/**
	 * appelle constructeur parent
	 * 
	 * @param game
	 *            le jeutest a afficher
	 */
	public PacmanPainter(Game game, SpriteFactory factory) {
		this.game = (World) game;
		spriteFactory = factory;
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

		Vector heroPos = game.getHero().getDrawPos();
		Vector heroSpeed = game.getHero().getSpeed();
        Graphics g = im.getGraphics();
        g.drawImage(spriteFactory.getHero().getAnimation(heroSpeed), (int) (heroPos.getX() * BLOCK_SIZE), (int) (heroPos.getY() * BLOCK_SIZE), null);


		crayon.setColor(Color.red);
		for (Monster m: game.getMonsterList()){
            Vector monsterPos = m.getDrawPos();
            Vector monsterSpeed = m.getSpeed();
            g.drawImage(spriteFactory.getEnemy().getAnimation(monsterSpeed), (int) (monsterPos.getX() * BLOCK_SIZE), (int) (monsterPos.getY() * BLOCK_SIZE), null);
		}

		drawLifePoint(im);
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

	public void drawLifePoint(BufferedImage img){
		Graphics g = img.getGraphics();
		g.drawImage(spriteFactory.getLife().getSprite(), 0, 0, null);
		g.drawString(game.getHero().getLifepoints()+"", BLOCK_SIZE, BLOCK_SIZE);
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
