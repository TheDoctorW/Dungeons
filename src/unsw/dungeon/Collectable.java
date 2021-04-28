package unsw.dungeon;

public abstract class Collectable extends Entity{
    /**
     * 
     * @param x x-coord
     * @param y y-coord
     */
    public Collectable(int x, int y) {
        super(x, y);
    }
    public abstract int getid();
    public abstract String getName();
    public abstract void collectItem();
    
}