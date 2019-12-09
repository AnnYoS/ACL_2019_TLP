package view;

import engine.Game;
import engine.GamePainter;
import math.Vector;
import model.World;
import model.cell.*;
import model.entity.Entity;
import model.entity.person.Monster;
import model.sprites.Sprite;
import model.sprites.SpriteAttack;
import model.sprites.SpriteFactory;
import model.sprites.SpritePerson;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

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

	protected int width;
	protected int height;

	private World game;

	private long dt;
	private long time;

	private SpriteAttack attack;
	private boolean attacking;

	private SpriteFactory spriteFactory;

	private Map<Object, Sprite> spriteMap;

	/**
	 * appelle constructeur parent
	 * 
	 * @param game
	 *            le jeutest a afficher
	 */
	public PacmanPainter(Game game, SpriteFactory factory) {
		this.game = (World) game;
		spriteFactory = factory;

		width = ((World) game).getMap().getW() * BLOCK_SIZE;
		height = ((World) game).getMap().getH() * BLOCK_SIZE;
		dt = 0;
		time = System.currentTimeMillis();

		spriteMap = new HashMap<>();
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		dt = System.currentTimeMillis() - time;
		time = System.currentTimeMillis();

		Graphics2D crayon = (Graphics2D) im.getGraphics();

		model.Map map = game.getMap();
		for(int i=0; i < map.getW();i++){
			for(int j=0; j < map.getH();j++){
				Cell c = map.getCell(i, j);
				c.draw(this, im, i, j);
			}
		}

		//Déplacement du héros
		Vector heroPos = game.getHero().getDrawPos();
		Vector heroSpeed = game.getHero().getSpeed();
        Graphics g = im.getGraphics();


		if(!spriteMap.containsKey(game.getHero())) {
			spriteMap.put(game.getHero(), spriteFactory.getHero());
		}
		Sprite tmp = spriteMap.get(game.getHero());
		g.drawImage(tmp.getAnimation(heroSpeed,dt), (int) (heroPos.getX() * BLOCK_SIZE), (int) (heroPos.getY() * BLOCK_SIZE), null);


        //Attaque du héros
		attacking = game.getHero().isAttacking();
		game.getHero().setIsAttacking(false);
		if(attacking){
			if(attack==null) {
				attack = (SpriteAttack) spriteFactory.getAttack();
			}
			g.drawImage(attack.getAnimation(heroSpeed,dt), (int) (heroPos.getX() * BLOCK_SIZE-32), (int) (heroPos.getY() * BLOCK_SIZE-32), null);
		}

		if(attack!= null && attack.isFinished()){
			attacking = false;
			attack = null;
		}


		crayon.setColor(Color.red);
		for (Monster m: game.getMonsterList()){
            Vector monsterPos = m.getDrawPos();
            Vector monsterSpeed = m.getSpeed();

            if(! spriteMap.containsKey(m)) {
            	spriteMap.put(m, spriteFactory.getEnemy());
			}
            tmp = spriteMap.get(m);

            g.drawImage(tmp.getAnimation(monsterSpeed, dt), (int) (monsterPos.getX() * BLOCK_SIZE), (int) (monsterPos.getY() * BLOCK_SIZE), null);
		}

		for(Entity e : game.getProjectiles()) {
			Vector pos = e.getDrawPos();

			pos.mult(BLOCK_SIZE);

			//g.setColor(Color.red);
			//g.fillRect((int)pos.getX() - 4 + BLOCK_SIZE / 2, (int)pos.getY()  - 4 + BLOCK_SIZE / 2, 8, 8);

			g.drawImage(spriteFactory.getFireball().getSprite(), (int)(pos.getX()), (int)(pos.getY()), null);
		}

		drawLifePoint(im);
	}

	public void drawCell(BufferedImage img, Grass gr, int x, int y) {
		Graphics g = img.getGraphics();
		g.drawImage(spriteFactory.getGrass().getSprite(), x * BLOCK_SIZE, y * BLOCK_SIZE, null);
	}

	public void drawCell(BufferedImage img, Wall w, int x, int y) {
		Graphics g = img.getGraphics();
		if(!w.isDestructible()) {
			g.drawImage(spriteFactory.getWall().getSprite(), x * BLOCK_SIZE, y * BLOCK_SIZE, null);
		}else{
			g.drawImage(spriteFactory.getWalldestruc().getSprite(), x * BLOCK_SIZE, y * BLOCK_SIZE, null);
		}
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

	public void drawCell(BufferedImage img, Warp p, int x, int y) {
		Graphics g = img.getGraphics();
		g.drawImage(spriteFactory.getGrass().getSprite(), x * BLOCK_SIZE, y * BLOCK_SIZE, null);

		if(! spriteMap.containsKey(p)) {
			spriteMap.put(p, spriteFactory.getWarp());
		}
		Sprite tmp = spriteMap.get(p);

		g.drawImage(tmp.getAnimation(new Vector(0,0), dt), x * BLOCK_SIZE, y * BLOCK_SIZE, null);
	}

	public void drawCell(BufferedImage img, Sand s, int x, int y) {
		Graphics g = img.getGraphics();
		g.drawImage(spriteFactory.getSand().getSprite(), x * BLOCK_SIZE, y * BLOCK_SIZE, null);
		//g.setColor(Color.CYAN);
		//g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
	}

	public void drawLifePoint(BufferedImage img){
		Graphics g = img.getGraphics();
		g.drawImage(spriteFactory.getLife().getSprite(), 0, 0, null);
		g.drawString(game.getHero().getLifepoints()+"", BLOCK_SIZE, BLOCK_SIZE);
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

}
