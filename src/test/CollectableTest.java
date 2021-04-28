package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.*;

public class CollectableTest {
    
    @Test
    public void BasicCollect() {
        Dungeon d = new Dungeon(3, 3);
        Player p = new Player(d, 0, 0);
        d.setPlayer(p);
        Key k = new Key(d,0,1,1);
        Sword s = new Sword(d,1,1);
        Treasure t1 = new Treasure(d,2,1);
        Treasure t2 = new Treasure(d,2,2);
        d.addCollectable(k);
        d.addCollectable(s);
        d.addCollectable(t1);
        d.addCollectable(t2);
        p.moveDown();
        assertEquals(p.getBag().size(), 1);
        assertEquals(d.findCollectableByCoord(0, 1), null);
        p.moveRight();
        assertEquals(p.getBag().size(), 2);
        assertEquals(d.findCollectableByCoord(1, 1), null);
        p.moveRight();
        assertEquals(p.getBag().size(), 3);
        assertEquals(d.findCollectableByCoord(2, 1), null);
        p.moveDown();
        assertEquals(p.getBag().size(), 4);
        assertEquals(d.findCollectableByCoord(2, 2), null);
    }

    @Test
    public void NotTwiceCollect() {
        Dungeon d = new Dungeon(3, 3);
        Player p = new Player(d, 0, 0);
        d.setPlayer(p);
        Key k1 = new Key(d,0,1,1);
        Sword s1 = new Sword(d,0,2);
        Key k2 = new Key(d,1,2,2);
        Sword s2 = new Sword(d,1,1);
        d.addCollectable(k1);
        d.addCollectable(s1);
        d.addCollectable(k2);
        d.addCollectable(s2);
        p.moveDown();
        assertEquals(p.getBag().size(), 1);
        p.moveDown();
        assertEquals(p.getBag().size(), 2);
        p.moveRight();
        assertEquals(p.getBag().size(), 2);
        assertEquals(d.findCollectableByCoord(1, 2), k2);
        p.moveUp();
        assertEquals(p.getBag().size(), 2);
        assertEquals(d.findCollectableByCoord(1, 1), s2);

    }
}

