package unsw.dungeon;

public class FloorSwitch extends Fixed {

    private String name;
    private String state;
    private Dungeon d;
    /**
     * 
     * @param d dungeon
     * @param x x-coord
     * @param y y-coord
     */
    public FloorSwitch(Dungeon d, int x, int y) {
        super(x, y);
        this.name = "Floor Switch";
        this.state = "normal";
        this.d = d;
    }

    @Override
    public int getID() {
        return 0;
    }
    /**
     * getter of the name
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * getter of the FloorSwitch
     */
    public String getState() {
        return state;
    }
    /**
     * setter of the FloorSwitch state
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean checkPlayerBarrier(int x, int y, String position) {
        Boulder b = findBoulder(this.getX(), this.getY());
        if (b != null) {
            return b.checkPlayerBarrier(x, y, position);
        }
        return true;
    }
    /**
     * check the FloorSwitch state
     */
    public void checkState() {
        if (findBoulder(this.getX(), this.getY()) != null) {
            this.setState("triggered");
            return;
        }
        this.setState("normal");
    }
    /**
     * find the boulder
     * @return
     */
    public Boulder findBoulder(int x, int y) {
        for (Fixed f : d.getFixList()) {
            if (f.getX() == x && f.getY() == y && f.getName().equals("Boulder")) {
                Boulder b = (Boulder) f;
                return b;
            }
        }
        return null;
    }

    @Override
    public boolean checkEnemyBarrier() {
        return true;
    }

}