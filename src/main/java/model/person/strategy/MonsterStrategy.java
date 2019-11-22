package model.person.strategy;

import math.Point;
import model.Map;
import math.Vector;
import model.person.Monster;

public interface MonsterStrategy {
    Vector move(Monster m, Map map, Point heroPos);
}
