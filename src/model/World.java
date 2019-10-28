package model;

import engine.Cmd;
import engine.Game;
import model.person.Hero;

public class World implements Game {
    private Map map;
    private Hero hero;

    public World() {
        //map = new Map();
        StaticMapFactory staticMapFactory = new StaticMapFactory();
        map = staticMapFactory.loadMap();
        hero = new Hero(new Point(0, 0));
    }

    public boolean moveHeroTo(Point p) {
        boolean res;

        Point tmp = hero.getPos();

        res = Math.abs(p.getX() - tmp.getX()) <= 1 && Math.abs(p.getY() - tmp.getY()) <= 1 && map.isWalkable(p);

        if(res) {
            hero.setPos(p);
        }

        return res;
    }

    public Point getHeroPos() {
        return hero.getPos();
    }

    @Override
    public void evolve(Cmd userCmd) {
        switch (userCmd) {
            case UP: {
                Point old = getHeroPos();
                Point n = new Point(old.getX(), old.getY() - 1);
                moveHeroTo(n);
                break;
            }
            case DOWN: {
                Point old = getHeroPos();
                Point n = new Point(old.getX(), old.getY() + 1);
                moveHeroTo(n);
                break;
            }case LEFT: {
                Point old = getHeroPos();
                Point n = new Point(old.getX() - 1, old.getY());
                moveHeroTo(n);
                break;
            }case RIGHT: {
                Point old = getHeroPos();
                Point n = new Point(old.getX() + 1, old.getY());
                moveHeroTo(n);
                break;
            }
            case IDLE:
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
