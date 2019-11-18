package model.person.strategy;

import model.Map;
import model.Point;

import java.util.*;

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
        int x;
        int y;
        double newDistance = 0.0;

        List<Integer> directions = new ArrayList<>();
        directions.add(1);
        directions.add(2);
        directions.add(3);
        directions.add(4);

        java.util.Map<Double, Point> distanceMap= new TreeMap<>();

        Collections.shuffle(directions);

        for (Integer i: directions) {
            x = pos.getX();
            y = pos.getY();
            switch (i) {
                case 1: // En haut
                    y = y - 1;
                    newDistance = Math.sqrt((heroPos.getX()-x)*(heroPos.getX()-x)+(heroPos.getY()-y)*(heroPos.getY()-y));
                    break;
                case 2: // A droite
                    x = x + 1;
                    newDistance = Math.sqrt((heroPos.getX()-x)*(heroPos.getX()-x)+(heroPos.getY()-y)*(heroPos.getY()-y));
                    break;
                case 3: // En bas
                    y = y + 1;
                    newDistance = Math.sqrt((heroPos.getX()-x)*(heroPos.getX()-x)+(heroPos.getY()-y)*(heroPos.getY()-y));
                    break;
                case 4: // A gauche
                    x = x - 1;
                    newDistance = Math.sqrt((heroPos.getX()-x)*(heroPos.getX()-x)+(heroPos.getY()-y)*(heroPos.getY()-y));
                    break;
            }
            Point newPos = new Point(x, y);
            if (map.isWalkable(newPos)) {
                distanceMap.put(newDistance,newPos);
            }
        }

        //System.out.println("Distance choisit : "+distanceMap.keySet().iterator().next());

        return distanceMap.get(distanceMap.keySet().iterator().next());

    }
}
