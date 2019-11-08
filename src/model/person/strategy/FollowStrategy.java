package model.person.strategy;

import model.Map;
import model.Point;

import java.util.Random;

public class FollowStrategy implements MonsterStrategy {
    private Random rand = new Random();

    @Override
    public Point move(Point pos, Map map, Point heroPos) {
        /*
        faire un tableau : [1, 2, 3, 4]
        shuffle le tableau
        prendre dans l'ordre les pos comme la suite de l'algo
        si aucun n'améliore prendre le premier possible
         */
        Point newPos = null;
        boolean exit = false;
        int x;
        int y;
        double newDistance = 0.0;
        double oldDistance = Math.sqrt((heroPos.getX()-pos.getX())*(heroPos.getX()-pos.getX())+(heroPos.getY()-pos.getY())*(heroPos.getY()-pos.getY()));
        while(!exit) {
            x = pos.getX();
            y = pos.getY();
            int dep = 1+rand.nextInt(4);
            switch (dep) {
                case 1: // En haut
                    y = y - 1;
                    newDistance = Math.sqrt((heroPos.getX()-pos.getX())*(heroPos.getX()-pos.getX())+(heroPos.getY()-y)*(heroPos.getY()-y));
                    break;
                case 2: // A droite
                    x = x + 1;
                    newDistance = Math.sqrt((heroPos.getX()-x)*(heroPos.getX()-x)+(heroPos.getY()-pos.getY())*(heroPos.getY()-pos.getY()));
                    break;
                case 3: // En bas
                    y = y + 1;
                    newDistance = Math.sqrt((heroPos.getX()-pos.getX())*(heroPos.getX()-pos.getX())+(heroPos.getY()-y)*(heroPos.getY()-y));
                    break;
                case 4: // A gauche
                    x = x - 1;
                    newDistance = Math.sqrt((heroPos.getX()-x)*(heroPos.getX()-x)+(heroPos.getY()-pos.getY())*(heroPos.getY()-pos.getY()));
                    break;
            }
            if (newDistance <= oldDistance || x == heroPos.getX() && y == heroPos.getY()) {
                newPos = new Point(x, y);
                if (map.isWalkable(newPos)) {
                    exit = true;
                }
            }
        }
        return newPos;
    }
}
