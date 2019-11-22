package model.person;

import model.Map;
import model.Vector;
import model.person.strategy.MonsterStrategy;

public class Monster implements Person {

    public static float SPEED = 0.0025f;

    private Vector pos;
    private Vector speed;
    private int lifepoints;
    private MonsterStrategy moveStrategy;

    public Monster(Vector pos, MonsterStrategy moveStrategy, int lp) {
        this.pos = pos;
        this.moveStrategy = moveStrategy;
        this.lifepoints = lp;
        speed = new Vector(0, 0);
    }

    @Override
    public void attack(Person p){
        p.looseLP(1);
    }

    @Override
    public void looseLP(int lp) {
        lifepoints = lifepoints - lp;
    }

    @Override
    public Vector getSpeed() {
        return speed;
    }

    public void setSpeed(Vector speed) {
        if(! speed.equals(this.speed)) {
            //pos.setX((int)(pos.getX()));
            //pos.setY((int)(pos.getY()));

            /*if(this.speed.getX() > 0) {
                pos.setX((int)pos.getX());
            }
            else if(this.speed.getX() < 0) {
                pos.setX((int)pos.getX() + 1);
            }
            else if(this.speed.getY() > 0) {
                pos.setY((int)pos.getY());
            }
            else if(this.speed.getY() < 0) {
                pos.setY((int)pos.getY() + 1);
            }*/
            pos.setX(pos.getXasInt());
            pos.setY(pos.getYasInt());

            this.speed = speed;
        }
    }

    @Override
    public void forceSetSpeed(Vector v) {
        if(this.speed.getX() > 0) {
            pos.setX((int)pos.getX());
        }
        else if(this.speed.getX() < 0) {
            pos.setX((int)pos.getX() + 1);
        }
        else if(this.speed.getY() > 0) {
            pos.setY((int)pos.getY());
        }
        else if(this.speed.getY() < 0) {
            pos.setY((int)pos.getY() + 1);
        }

        this.speed = v;
    }


    public Vector getPos() {
        return pos;
    }

    public void setPos(Vector pos) {
        this.pos = pos;
    }

    public void calcSpeed(Map map, Vector heroPos) {
        Vector tmp =  moveStrategy.move(this.pos, map, heroPos);
        setSpeed(tmp);
    }

    public int getLifepoints() { return lifepoints; }
}
