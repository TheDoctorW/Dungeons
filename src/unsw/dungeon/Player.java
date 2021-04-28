package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private List<Collectable> bag;
    private PlayerStatus status;
    private int lifes;
    
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.bag = new ArrayList<Collectable>();
        this.status = new Alive();
        this.lifes = 3;
    }
    /**
     * player move up
     */
    public void moveUp() {
        if (checkIfPortal("up")) {
            dungeon.updateGame();
        } else if (checkMove("up")) {
            y().set(getY() - 1);
            dungeon.updateGame();
        } 
    }
    /**
     * player move down
     */
    public void moveDown() {
        if (checkIfPortal("down")) {
            dungeon.updateGame();
        } else if (checkMove("down")) {
            y().set(getY() + 1);
            dungeon.updateGame();
        } 
    }
    /**
     * player move left
     */
    public void moveLeft() {
        if (checkIfPortal("left")) {
            dungeon.updateGame();
        } else if (checkMove("left")) {
            x().set(getX() - 1);
            dungeon.updateGame();
        }
    }
    /**
     * player move right
     */
    public void moveRight() {
        if (checkIfPortal("right")) {
            dungeon.updateGame();
        } else if (checkMove("right")) {
            x().set(getX() + 1);
            dungeon.updateGame();
        }
    }
    /**
     * the getter of status
     * @return the status of player
     */
    public PlayerStatus getStatus() {
        return status;
    }
    /**
     * the setter of status
     * @param status
     */
    public void setStatus(PlayerStatus status) {
        this.status = status;
    }
    /**
     * the getter of bag list
     * @return the item list in player bag
     */
    public List<Collectable> getBag() {
        return bag;
    }
    /**
     * @param c collectable item
     */
    public void addBag(Collectable c) {
        c.collectItem();
    }
    /**
     * add the collectable item to bag list
     * @param c
     */
    public void addBagList(Collectable c) {
        bag.add(c);
    }
    /**
     * remove the collectable item in bag
     */
    public void removeBag(Collectable c) {
        bag.remove(c);
    }
    /**
     * check the bag item
     */
    public Collectable checkBag(String s) {
        for(Collectable item: bag) {
            if(item.getName().equals(s)) {
                return item;
            }
        }
        return null;
    }
    /**
     * check the whether can move in different position
     * @param position
     * @return
     */
    public boolean checkMove(String position) {
        switch (position) {
            case "up":
                return (getY() > 0 && dungeon.checkPlayerBarrier(getX(), getY() - 1, "up") && dungeon.checkIfGameOver());
            case "down":
                return ((getY() < dungeon.getHeight() - 1) && dungeon.checkPlayerBarrier(getX(), getY() + 1, "down") && dungeon.checkIfGameOver());
            case "left":
                return (getX() > 0 && dungeon.checkPlayerBarrier(getX() - 1, getY(), "left") && dungeon.checkIfGameOver());
            case "right":
                return ((getX() < dungeon.getWidth() - 1) && dungeon.checkPlayerBarrier(getX() + 1, getY(), "right") && dungeon.checkIfGameOver());
        }
        // System.out.println("Player Check False!");
        return false;
    }
    /**
     * player meet the enemy
     * @param x
     * @param y
     */
    public void meetEnemy(int x, int y) {
        Enemy e = dungeon.findEnemyByCoord(x, y);
        if (e == null) {
            for (Enemy en : dungeon.getEnemies()) {
                en.setCheck(true);
            }
            return;
        }
        if (this.status.equals(new Invincible())) {
            e.visibility().set(false);
            dungeon.removeEnemies(e);
            return;
        } else if (this.checkBag("Sword") != null) {
            if (e.getName().equals("Soldier")) {
                e.visibility().set(false);
                dungeon.removeEnemies(e);
                Sword s = (Sword) this.checkBag("Sword");
                s.setFrequency(s.getFrequency() - 1);
                if (s.getFrequency() == 0) {
                    this.removeBag(s);
                }
                return;
            }
        }
        if (e.getName().equals("Soldier")) {
            this.lifes --;
            e.setCheck(false);
        }
        if (this.lifes <= 0 || e.getName().equals("Boss")) {
            this.visibility().set(false);
            this.status.Dead(this);
        }
    }

    public boolean checkIfPlayerDead() {
        if (getStatus().equals(new Dead())) {
            return false;
        }
        return true;
    }

    public boolean checkIfPortal(String position) {
        int x = getX();
        int y = getY();
        if (position.equals("up")) {
            y --;
            if (y < 0) {return false;}
        } else if (position.equals("down")) {
            y ++;
            if (y >= dungeon.getHeight()) {return false;}
        } else if (position.equals("left")) {
            x --;
            if (x < 0) {return false;}
        } else if (position.equals("right")) {
            x ++;
            if (x >= dungeon.getWidth()) {return false;}
        }
        for (Fixed f : dungeon.getFixList()) {
            if (f.getX() == x && f.getY() == y && f.getName().equals("Portal")) {
                return checkMove(position);
            }
        }
        return false;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

}
