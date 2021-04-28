package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.*;

public class UseItemTest {

    @Test
    public void useKeyTest() {
        Dungeon d = new Dungeon(3, 3);
        Player p = new Player(d, 0, 0);
        d.setPlayer(p);
        Door d1 = new Door(d, 1, 0, 0);
        d.addFixed(d1);
        Door d2 = new Door(d, 1, 1, 1);
        d.addFixed(d2);
        Key k = new Key(d, 0, 1, 1);
        d.addCollectable(k);
        p.moveRight();
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 0);
        p.moveDown();
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 1);
        p.moveRight();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
        assertEquals(p.getBag().size(), 0);
        p.moveRight();
        p.moveLeft();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
    }

    @Test 
    public void UsingPotion() {
        Dungeon d = new Dungeon(3, 3);
        Player p = new Player(d, 0, 0);
        d.setPlayer(p);
        Potion potion = new Potion(d,0,1);
        d.addCollectable(potion);
        assertTrue(p.getStatus().equals(new Alive()));
        p.moveDown();
        assertEquals(d.findCollectableByCoord(0, 1), null);
        assertTrue(p.getStatus().equals(new Invincible()));
        try {
            Thread.sleep(2500);
        } catch (InterruptedException i) {
			i.printStackTrace();
        }
        assertTrue(p.getStatus().equals(new Alive()));
        
    }

    @Test 
    public void TwiceUsingPotion() {
        Dungeon d = new Dungeon(3, 3);
        Player p = new Player(d,0,0);
        d.setPlayer(p);
        Potion potion1 = new Potion(d,0,1);
        Potion potion2 = new Potion(d,1,1);
        d.addCollectable(potion1);
        d.addCollectable(potion2);
        assertTrue(p.getStatus().equals(new Alive()));
        p.moveDown();
        assertEquals(d.findCollectableByCoord(0, 1), null);
        assertTrue(p.getStatus().equals(new Invincible()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException i) {
			i.printStackTrace();
        }
        assertTrue(p.getStatus().equals(new Invincible()));
        p.moveRight();
        assertEquals(d.findCollectableByCoord(1, 1), null);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException i) {
			i.printStackTrace();
        }
        assertTrue(p.getStatus().equals(new Alive()));
    }
    
}