package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.*;

public class EnemyTest {

    @Test
    public void haveNothingTest() {
        Dungeon d = new Dungeon(3, 3);
        Player p = new Player(d, 0, 1);
        Boss e = new Boss(d, 2, 1);
        d.setPlayer(p);
        d.addEnemies(e);
        assertEquals(d.getEnemies().size(), 1);
        p.moveRight();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
        assertEquals(e.getX(), 1);
        assertEquals(e.getY(), 1);
        assertEquals(p.getStatus(), new Dead());
        assertEquals(d.goalState().get(), false);
        assertEquals(d.playerState().get(), true);
        assertEquals(d.checkIfGameOver(), false);
        assertEquals(p.checkMove("left"), false);
        assertEquals(p.checkMove("up"), false);
        assertEquals(p.checkMove("down"), false);
        p.moveLeft();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
        assertEquals(e.getX(), 1);
        assertEquals(e.getY(), 1);
        p.moveRight();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
        assertEquals(e.getX(), 1);
        assertEquals(e.getY(), 1);
    }

    @Test
    public void haveSwordTest() {
        Dungeon d = new Dungeon(5, 1);
        Player p = new Player(d, 0, 0);
        Soldier e = new Soldier(d, 4, 0);
        d.setPlayer(p);
        d.addEnemies(e);
        Sword s = new Sword(d, 1, 0);
        d.addCollectable(s);
        p.moveRight();
        assertEquals(s.getFrequency(), 5);
        assertEquals(p.checkBag("Sword"), s);
        p.moveRight();
        assertEquals(d.getEnemies().size(), 0);
        assertEquals(s.getFrequency(), 4);
        p.moveRight();
        assertEquals(p.getX(), 3);
        assertEquals(p.getY(), 0);
    }

    @Test
    public void havePotionTest() {
        Dungeon d = new Dungeon(4, 1);
        Player p = new Player(d, 0, 0);
        Boss e = new Boss(d, 2, 0);
        d.setPlayer(p);
        d.addEnemies(e);
        Potion po = new Potion(d, 1, 0);
        d.addCollectable(po);
        p.moveRight();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 0);
        assertEquals(p.getStatus(), new Invincible());   
        assertEquals(e.getX(), 3);
        assertEquals(e.getY(), 0);
        p.moveRight();
        assertEquals(p.getStatus(), new Invincible()); 
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 0);
        assertEquals(e.getX(), 3);
        assertEquals(e.getY(), 0);
        p.moveRight();
        assertEquals(e.getX(), 3);
        assertEquals(e.getY(), 0);
        assertEquals(p.getX(), 3);
        assertEquals(p.getY(), 0);
        assertEquals(d.getEnemies().size(), 0);
    }
    
}