package model.entity.person.strategy;

import math.Point;
import model.Map;
import math.Vector;
import model.entity.person.Monster;

public interface MonsterStrategy {
    Vector move(Monster m, Map map, Point heroPos);
}
