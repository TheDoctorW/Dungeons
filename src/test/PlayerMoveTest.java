package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.*;

public class PlayerMoveTest {

    @Test
    public void basicMoveTest() {
        Dungeon d = new Dungeon(3, 3);
        Player p = new Player(d, 1, 1);
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
        d.setPlayer(p);
        p.moveUp();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 0);
        p.moveUp();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 0);
        p.moveDown();
        p.moveDown();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 2);
        p.moveDown();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 2);
        p.moveUp();
        p.moveLeft();
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 1);
        p.moveLeft();
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 1);
        p.moveRight();
        p.moveRight();
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);
        p.moveRight();
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);
    }

    @Test
    public void barrierMoveTest() {
        Dungeon d = new Dungeon(3, 3);
        Player p = new Player(d, 1, 1);
        d.setPlayer(p);
        Wall w = new Wall(0, 1);
        Door doo = new Door(d, 1, 0, 0);
        Boulder b = new Boulder(d, 1, 2);
        d.addFixed(b);
        d.addFixed(doo);
        d.addFixed(w);
        p.moveDown();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
        p.moveLeft();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
        p.moveUp();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
        p.moveRight();
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);
    }

    @Test
    public void boulderMoveTest() {
        Dungeon d = new Dungeon(3, 3);
        Player p = new Player(d, 0, 1);
        d.setPlayer(p);
        Boulder b = new Boulder(d, 1, 1);
        d.addFixed(b);
        FloorSwitch f = new FloorSwitch(d, 2, 1);
        d.addFixed(f);
        assertEquals(f.getState(), "normal");
        p.moveRight();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
        assertEquals(f.getX(), 2);
        assertEquals(f.getY(), 1);
        assertEquals(d.getFixList().size(), 2);
        assertEquals(f.findBoulder(f.getX(), f.getY()), b);
        assertEquals(f.getState(), "triggered");
        p.moveRight();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
        p.moveLeft();
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 1);
        assertEquals(b.getX(), 2);
        assertEquals(b.getY(), 1);
    }

    @Test
    public void exitMoveTest() {
        Dungeon d = new Dungeon(3, 3);
        Player p = new Player(d, 0, 1);
        d.setPlayer(p);
        Exit e = new Exit(2, 1);
        d.addFixed(e);
        p.moveRight();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
        p.moveRight();
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);
        p.moveLeft();
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);
        p.moveUp();
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);
        p.moveDown();
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);
    }
    
    @Test
    public void portalMoveUpTest() {
        Dungeon d = new Dungeon(3, 3);
        Player p = new Player(d, 1, 2);
        d.setPlayer(p);
        Portal p1 = new Portal(d, 1, 1, 0);
        Portal p2 = new Portal(d, 2, 2, 0);
        d.addFixed(p1);
        d.addFixed(p2);
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 2);
        p.moveUp();
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 2);
        p.moveLeft();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 2);
    }

    @Test
    public void portalMoveDownTest() {
        Dungeon d = new Dungeon(3, 3);
        Player p = new Player(d, 1, 0);
        d.setPlayer(p);
        Portal p1 = new Portal(d, 1, 1, 0);
        Portal p2 = new Portal(d, 2, 2, 0);
        d.addFixed(p1);
        d.addFixed(p2);
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 0);
        p.moveDown();
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 2);
    }

    @Test
    public void portalMoveLeftTest() {
        Dungeon d = new Dungeon(3, 3);
        Player p = new Player(d, 2, 1);
        d.setPlayer(p);
        Portal p1 = new Portal(d, 1, 1, 0);
        Portal p2 = new Portal(d, 2, 2, 0);
        d.addFixed(p1);
        d.addFixed(p2);
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);
        p.moveLeft();
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 2);
    }

    @Test
    public void portalMoveRightTest() {
        Dungeon d = new Dungeon(3, 3);
        Player p = new Player(d, 0, 1);
        d.setPlayer(p);
        Portal p1 = new Portal(d, 1, 1, 0);
        Portal p2 = new Portal(d, 2, 2, 0);
        d.addFixed(p1);
        d.addFixed(p2);
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 1);
        p.moveRight();
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 2);
    }

}