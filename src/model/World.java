package model;

import model.person.Hero;

public class World {
    private Map map;
    private Hero hero;

    public World() {
        map = new Map();
        hero = new Hero(new Point(0, 0));
    }

    public boolean moveHeroTo(Point p) {
        boolean res = true;

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
}
