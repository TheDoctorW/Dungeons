package unsw.dungeon;

public class Wall extends Fixed {

    private String name;
    /**
     * Wall
     * @param x x-coord
     * @param y y-coord
     */
    public Wall(int x, int y) {
        super(x, y);
        this.name = "Wall";
    }

    @Override
    public int getID() {
        return 0;
    }
    /**
     * get the name of wall
     */
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public boolean checkPlayerBarrier(int x, int y, String position) {
        return false;
    }

    @Override
    public boolean checkEnemyBarrier() {
        return false;
    }

}
