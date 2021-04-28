package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.event.TreeSelectionEvent;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.*;

public class GoalTest {

    @Test
    public void TreasureGoal_And_ExitTest() {
        Dungeon d = new Dungeon(3, 3);
        Player p = new Player(d, 0, 0);
        d.setPlayer(p);
        Exit e = new Exit(1,2);
        Treasure t1 = new Treasure(d, 0, 2);
        Treasure t2 = new Treasure(d, 0, 1);
        Goal g1 = new ExitGoal();
        Goal g2 = new TreasureGoal();
        Goal and = new TwoAndGoal(g1, g2);
        d.addFixed(e);
        d.addCollectable(t1);
        d.addCollectable(t2);
        d.setGoal(and);
        p.moveDown();
        p.moveDown();
        assertEquals(p.getBag().size(), 2);
        p.moveRight();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 2);
        p.moveRight();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 2);
        assertTrue(d.getGoal().checkAchieve(d, p));
    }

    @Test
    public void TreasureGoal_AND_FloorSwitchTest() {
        Dungeon d = new Dungeon(5, 5);
        Player p = new Player(d, 0, 0);
        d.setPlayer(p);
        Goal g1 = new TreasureGoal();
        Goal g2 = new FloorSwitchGoal();
        Goal and = new TwoAndGoal(g1, g2);
        Exit e = new Exit(2,3);
        Treasure t1 = new Treasure(d, 0, 1);
        Treasure t2 = new Treasure(d, 1, 2);
        FloorSwitch fs = new FloorSwitch(d, 3, 2);
        Boulder b = new Boulder(d, 2, 2);
        d.addCollectable(t1);
        d.addCollectable(t2);
        d.addFixed(e);
        d.addFixed(fs);
        d.addFixed(b);
        d.setGoal(and);
        p.moveDown();
        p.moveRight();
        p.moveDown();
        assertFalse(d.getGoal().checkAchieve(d, p));
        p.moveRight();
        p.moveDown();
        assertTrue(d.getGoal().checkAchieve(d, p));
    }
    /**
     * Treasure Goal completed
     */
    @Test
    public void TreasureGoal_Or_FloorSwitchTest1() {
        Dungeon d = new Dungeon(5, 5);
        Player p = new Player(d, 0, 0);
        d.setPlayer(p);
        Goal g1 = new TreasureGoal();
        Goal g2 = new FloorSwitchGoal();
        Goal or = new TwoOrGoal(g1, g2);
        Exit e = new Exit(2,3);
        Treasure t1 = new Treasure(d, 0, 1);
        Treasure t2 = new Treasure(d, 1, 2);
        FloorSwitch fs = new FloorSwitch(d, 3, 2);
        Boulder b = new Boulder(d, 2, 2);
        d.addCollectable(t1);
        d.addCollectable(t2);
        d.addFixed(e);
        d.addFixed(fs);
        d.addFixed(b);
        d.setGoal(or);
        p.moveDown();
        p.moveRight();
        p.moveDown();
        assertTrue(d.getGoal().checkAchieve(d, p));
    
    }
    /**
     * FloorSwitch Goal completed
     */
    @Test
    public void TreasureGoal_Or_FloorSwitchTest2() {
        Dungeon d = new Dungeon(5, 5);
        Player p = new Player(d, 0, 0);
        d.setPlayer(p);
        Goal g1 = new TreasureGoal();
        Goal g2 = new FloorSwitchGoal();
        Goal or = new TwoOrGoal(g1, g2);
        Exit e = new Exit(1,2);
        Treasure t1 = new Treasure(d, 0, 1);
        Treasure t2 = new Treasure(d, 0, 2);
        FloorSwitch fs = new FloorSwitch(d, 2, 1);
        Boulder b = new Boulder(d, 1, 1);
        d.addCollectable(t1);
        d.addCollectable(t2);
        d.addFixed(e);
        d.addFixed(fs);
        d.addFixed(b);
        d.setGoal(or);
        p.moveDown();
        assertFalse(d.getGoal().checkAchieve(d, p));
        p.moveRight();
        assertTrue(d.getGoal().checkAchieve(d, p));
        p.moveDown();
        assertTrue(d.getGoal().checkAchieve(d, p));
    }

    @Test 
    public void EnemyGoalTest() {
        Dungeon d = new Dungeon(4, 1);
        Player p = new Player(d, 0, 0);
        Soldier e = new Soldier(d, 2, 0);
        d.setPlayer(p);
        d.addEnemies(e);
        Potion po = new Potion(d, 1, 0);
        Goal g = new EnemyGoal();
        d.setGoal(g);
        d.addCollectable(po);
        p.moveRight();
        p.moveRight();
        p.moveRight();
        assertTrue(d.getGoal().checkAchieve(d, p));

    }

    @Test 
    public void EnemyGoal_AND_ExitGoal() {
        Dungeon d = new Dungeon(4, 1);
        Player p = new Player(d, 0, 0);
        Soldier e = new Soldier(d, 3, 0);
        d.setPlayer(p);
        d.addEnemies(e);
        Exit exit = new Exit(3,0);
        Sword s = new Sword(d, 1, 0);
        Goal g1 = new EnemyGoal();
        Goal g2 = new ExitGoal();
        Goal and = new TwoAndGoal(g1, g2);
        d.setGoal(and);
        d.addFixed(exit);
        d.addCollectable(s);
        p.moveRight();
        p.moveRight();
        p.moveRight();
        assertTrue(d.getGoal().checkAchieve(d, p));
        
    }
    
}