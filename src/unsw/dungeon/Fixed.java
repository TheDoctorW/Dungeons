package unsw.dungeon;

public abstract class Fixed extends Entity{
    
    public Fixed(int x, int y) {
        super(x, y);
    }
    
    public abstract int getID();
    public abstract String getName();
    public abstract boolean checkPlayerBarrier(int x, int y, String position);
    public abstract boolean checkEnemyBarrier();

}