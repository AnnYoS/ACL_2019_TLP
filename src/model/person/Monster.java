package model.person;

import model.Map;
import model.Point;
import model.person.strategy.MonsterStrategy;

public class Monster implements Person {
    private Point pos;
    private int lifepoints;
    private MonsterStrategy moveStrategy;

    public Monster(Point pos, MonsterStrategy moveStrategy, int lp) {
        this.pos = pos;
        this.moveStrategy = moveStrategy;
        this.lifepoints = lp;
    }

    @Override
    public void attack(Person p){
        p.looseLP(10);
    }

    @Override
    public void looseLP(int lp) {
        lifepoints = lifepoints - lp;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public MonsterStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public Point getMove(Map map, Point heroPos){
        return moveStrategy.move(this.pos, map, heroPos);
    }

    public int getLifepoints() { return lifepoints; }
}
