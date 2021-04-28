/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private List<Collectable> collectableList;
    private List<Fixed> fixList;
    private List<Enemy> enemies;
    private Player player;
    private Goal goal;
    private BooleanProperty goalState;
    private BooleanProperty playerState;
    /**
     * 
     * @param width dungeon width
     * @param height dungeon height
     */
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.collectableList = new ArrayList<Collectable>();
        this.fixList = new ArrayList<Fixed>();
        this.enemies = new ArrayList<Enemy>();
        this.player = new Player(this, 0, 0);
        this.goal = new ExitGoal();
        // this.state = "start";
        this.goalState = new SimpleBooleanProperty(false);
        this.playerState = new SimpleBooleanProperty(false);
    }
    /**
     * getter of the dungeon width
     * @return width
     */
    public int getWidth() {
        return width;
    }
    /**
     * getter of the dungeon height
     * @return
     */
    public int getHeight() {
        return height;
    }
    /**
     * getter of the player
     * @return
     */
    public Player getPlayer() {
        return player;
    }
    /**
     * setter of the player
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    /**
     * add entity
     * @param entity
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    /**
     * remove the entity
     */
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }
    /**
     * add enemies
     * @param enemy
     */
    public void addEnemies(Enemy enemy) {
        enemies.add(enemy);
    }
    /**
     * remove enemies
     * @param enemy
     */
    public void removeEnemies(Enemy enemy) {
        enemies.remove(enemy);
    }
    /**
     * add collectable item
     * @param c
     */
    public void addCollectable(Collectable c) {
        collectableList.add(c);
    }
    /**
     * remove collectable item
     * @param c
     */
    public void removeCollectable(Collectable c) {
        collectableList.remove(c);
    }
    /**
     * add fixed item
     * @param f
     */
    public void addFixed(Fixed f) {
        fixList.add(f);
    }
    /**
     * remove fixed item
     * @param f
     */
    public void removeFixed(Fixed f) {
        fixList.remove(f);
    }
    /**
     * check the player move position whether has barrier
     * @param x x-coord
     * @param y y-coord
     * @param position 
     * @return boolean true or false
     */
    public boolean checkPlayerBarrier(int x, int y, String position) {
        Fixed f = findFixedByCoord(x, y);
        if (f != null) {
            return f.checkPlayerBarrier(x, y, position);
        }
        return true;
    } 
    /**
     * check the enemy whether has barrier
     * @param x x-coord
     * @param y y-coord
     * @return
     */
    public boolean checkEnemyBarrier(int x, int y) {
        Fixed f = findFixedByCoord(x, y);
        if (f != null) {
            return f.checkEnemyBarrier();
        }
        return true;
    }
    /**
     * find the fixed item by coord
     * @param x x-coord
     * @param y y-coord
     * @return the fixed item or null
     */
    public Fixed findFixedByCoord(int x, int y) {
        if (x >= 0 && y >= 0 && (y < getHeight()) && (x < getWidth())) {
            for (Fixed f : fixList) {
                if (f.getX() == x && f.getY() == y) {
                    return f;
                }
            }
        }
        return null;
    }
    /**
     * find the enemy by coord
     * @param x x-coord
     * @param y y-coord
     * @return Enemy or null
     */
    public Enemy findEnemyByCoord(int x, int y) {
        if (x >= 0 && y >= 0 && (y < getHeight()) && (x < getWidth())) {
            for (Enemy e : enemies) {
                if (e.getX() == x && e.getY() == y) {
                    return e;
                }
            }
        }
        return null;
    }
    /**
     * find the collectable item by coord
     * @param x x-coord
     * @param y y-coord
     * @return the collectable item
     */
    public Collectable findCollectableByCoord(int x, int y) {
        if (x >= 0 && y >= 0 && (y < getHeight()) && (x < getWidth())) {
            for (Collectable c : collectableList) {
                if (c.getX() == x && c.getY() == y) {
                    return c;
                }
            }
        }
        return null;
    }
    /**
     * update the floorSwitch 
     */
    public void updateFloorSwitch() {
        for (Fixed f : fixList) {
            if (f.getName().equals("Floor Switch")) {
                FloorSwitch fs = (FloorSwitch) f;
                fs.checkState();
            }
        }
    }
    /**
     * update the enemy
     */
    public void updateEnemy() {
        for (Enemy e : enemies) {
            e.moveEnemy();
        }
    }
    /**
     * getter of the collectable list
     */
    public List<Collectable> getCollectableList() {
        return collectableList;
    }
    /**
     * getter of the goal
     */
    public Goal getGoal() {
        return goal;
    }
    /**
     * setter of the goal
     */
    public void setGoal(Goal goal) {
        this.goal = goal;
    }
    /**
     * check the goal whether has achieve
     * @return boolean true or false
     */
    public boolean checkGoal() {
        return goal.checkAchieve(this, player);
    }
    /**
     * update game state
     */
    public void updateGame() {
        Collectable c = findCollectableByCoord(player.getX(), player.getY());
        if(c != null) {
            player.addBag(c);
        }
        this.updateFloorSwitch();
        this.updateEnemy();
        player.meetEnemy(player.getX(), player.getY());
        // for (Enemy e : enemies) {
        //     e.meetPlayer();
        // }
        if(this.checkGoal()) {
            goalState().set(true);
            // System.out.println("Game Over!");
        } else if (player.getStatus().equals(new Dead())) {
            playerState().set(true);
        } else {
            goalState().set(false);
            playerState().set(false);
        }
    }
    /**
     * getter of the fixed list
     */
    public List<Fixed> getFixList() {
        return fixList;
    }
    /**
     * getter of the goal state
     * @return
     */
    public BooleanProperty goalState() {
        return goalState;
    }
    /**
     * getter of the player state
     * @return
     */
    public BooleanProperty playerState() {
        return playerState;
    }
    /**
     * check the game whether has over
     * @return
     */
    public boolean checkIfGameOver() {
        if (goalState().get() == true || playerState().get() == true) {
            return false;
        }
        return true;
    }
    /**
     * setter of the player position
     * @param x x-coord
     * @param y y-coord
     */
    public void setPlayerPosition(int x, int y) {
        player.x().set(x);
        player.y().set(y);
    }
    /**
     * getter of the enemies list
     * @return enemy
     */
    public List<Enemy> getEnemies() {
        return enemies;
    }
    /**
     * find number of fixed item by name
     * @param name name
     * @return number
     */
    public int getFixedNumberByName(String name) {
        int n = 0;
        for (Fixed f : fixList) {
            if (f.getName().equals(name)) {
                n ++;
            }
        }
        return n;
    }
    /**
     * find number of collectable item by name
     * @param name name
     * @return number
     */
    public int getCollectableNumberByName(String name) {
        int n = 0;
        for (Collectable c : collectableList) {
            if (c.getName().equals(name)) {
                n ++;
            }
        }
        return n;
    }

}
