package modelTest.cellTest;

import math.Point;
import model.cell.Warp;
import model.person.Hero;
import model.person.Monster;
import model.person.strategy.RandomStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WarpTest {

    @Test
    public void testSetDest(){
        Point p = new Point(1,0);
        Warp warp = new Warp();
        warp.setDest(p);

        assertEquals(new Point(1,0), warp.getDest());
    }

    @Test
    public void testApplyEffectOnHero(){
        Point p = new Point(1,0);
        Warp warp = new Warp();
        warp.setDest(p);

        Hero hero = new Hero(new Point(42,42), 10);

        warp.applyEffect(hero);

        assertEquals(new Point(1,0), hero.getPos());
    }

    @Test
    public void testApplyEffectOnMonster(){
        Point p = new Point(1,0);
        Warp warp = new Warp();
        warp.setDest(p);

        Monster monster = new Monster(new Point(42,42), new RandomStrategy(), 10);

        warp.applyEffect(monster);

        assertEquals(new Point(42,42), monster.getPos());
    }

    @Test
    public void testApplyEffectPointNull(){
        Point p = null;
        Warp warp = new Warp();
        warp.setDest(p);

        Hero hero = new Hero(new Point(42,42), 10);

        warp.applyEffect(hero);

        assertEquals(new Point(42,42), hero.getPos());
    }

    @Test
    public void testApplyEffectPersonNull(){
        Point p = new Point(1,0);
        Warp warp = new Warp();
        warp.setDest(p);

        assertThrows(NullPointerException.class, ()->{warp.applyEffect(null);});
    }
}
