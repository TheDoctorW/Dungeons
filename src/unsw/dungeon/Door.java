package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Fixed {

    private int id;
    private String name;
    // private String state;
    private BooleanProperty state;
    private Dungeon d;
    /**
     * the fixed item door
     * @param d dungeon
     * @param x x-coord
     * @param y y-coord
     * @param id the id of door
     */
    public Door(Dungeon d, int x, int y, int id) {
        super(x, y);
        this.id = id;
        this.name = "Door";
        // this.state = "close";
        this.state = new SimpleBooleanProperty(false);
        this.d = d;
    }
    /**
     * the getter of id
     */
    @Override
    public int getID() {
        return id;
    }
    /**
     * the getter of name
     */
    @Override
    public String getName() {
        return name;
    }
    
    public BooleanProperty state() {
        return state;
    }

    public boolean getState() {
        return state().get();
    }

    /**
     * check the barrier
     */
    @Override
    public boolean checkPlayerBarrier(int x, int y, String position) {
        return checkBarrierDoor();
    }
    /**
     * check the key whether match that door
     * @param p player
     * @return key or null
     */
    public Key checkKey(Player p) {
        for (Collectable c : p.getBag()) {
            if (c.getName().equals("Key") && c.getid() == this.id) {
                p.removeBag(c);
                state().set(true);
                return (Key) c;
            }
        }
        return null;
    }
    /**
     * check the player whether can open the door
     * when the door state is close
     */
    public boolean checkBarrierDoor() {
        if (state().get() == false) {
            if (this.checkKey(d.getPlayer()) == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkEnemyBarrier() {
        if (state().get() == true) {
            return true;
        }
        return false;
    }
    
}