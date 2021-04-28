package unsw.dungeon;

public class Portal extends Fixed {

    private int id;
    private String name;
    private Dungeon d;
    /**
     * Portal
     * @param d dungeon
     * @param x x-coord
     * @param y y-coord
     * @param id the portal id 
     */
    public Portal(Dungeon d, int x, int y, int id) {
        super(x, y);
        this.id = id;
        this.name = "Portal";
        this.d = d;
    }
    /**
     * getter of the portal id
     */
    @Override
    public int getID() {
        return id;
    }
    /**
     * getter of the portal name
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * check the barrier of the different position
     */
    @Override
    public boolean checkPlayerBarrier(int x, int y, String position) {
        int newx = 0;
        int newy = 0;
        for (Fixed f : d.getFixList()) {
            if (f.getName().equals("Portal") && (f.getX() != this.getX() || f.getY() != this.getY()) && f.getID() == this.getID()) {
                newx = f.getX();
                newy = f.getY();
            }
        }
        d.setPlayerPosition(newx, newy);
        return true;
    }

    @Override
    public boolean checkEnemyBarrier() {
        return false;
    }
    
}