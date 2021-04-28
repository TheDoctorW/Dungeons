package unsw.dungeon;

public class Exit extends Fixed {

    private String name;
    /**
     * 
     * @param x x-coord
     * @param y y-coord
     */
    public Exit(int x, int y) {
        super(x, y);
        this.name = "Exit";
    }

    @Override
    public int getID() {
        return 0;
    }
    /**
     * getter of the exit name
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean checkPlayerBarrier(int x, int y, String position) {
        return true;
    }

    @Override
    public boolean checkEnemyBarrier() {
        return false;
    }

}