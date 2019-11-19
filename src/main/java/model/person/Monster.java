package model.person;

import model.Map;
import model.Vector;
import model.person.strategy.MonsterStrategy;

public class Monster implements Person {
    private Vector pos;
    private int lifepoints;
    private MonsterStrategy moveStrategy;

    public Monster(Vector pos, MonsterStrategy moveStrategy, int lp) {
        this.pos = pos;
        this.moveStrategy = moveStrategy;
        this.lifepoints = lp;
    }

    @Override
    public void attack(Person p){
        p.looseLP(1);
    }

    @Override
    public void looseLP(int lp) {
        lifepoints = lifepoints - lp;
    }

    public Vector getPos() {
        return pos;
    }

    public void setPos(Vector pos) {
        this.pos = pos;
    }

    public MonsterStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public Vector getMove(Map map, Vector heroPos){
        return moveStrategy.move(this.pos, map, heroPos);
    }

    public int getLifepoints() { return lifepoints; }
}
