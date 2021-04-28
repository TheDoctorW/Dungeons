package unsw.dungeon;

public class Boulder extends Fixed {

    private String name;
    private Dungeon d;
    /**
     * 
     * @param d
     * @param x
     * @param y
     */
    public Boulder(Dungeon d, int x, int y) {
        super(x, y);
        this.name = "Boulder";
        this.d = d;
    }
    @Override
    public int getID() {
        return 0;
    }
    /**
     * the getter of name 
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * check the barrier of different position
     */
    @Override
    public boolean checkPlayerBarrier(int x, int y, String position) {
        boolean check = false;
        check = checkBarrierBoulder(x, y, position);
        if (check) {
            if (position.equals("up")) {
                y --;
                y().set(y);
            } else if (position.equals("down")) {
                y ++;
                y().set(y);
            } else if (position.equals("left")) {
                x --;
                x().set(x);
            } else if (position.equals("right")) {
                x ++;
                x().set(x);
            }
        }
        return check;
    }
    /**
     * check the different move positon whether has 2 barrier
     * cannot move 2 boulders
     * check the boulders whether has wall
     * @param x x-coord
     * @param y y-coord
     * @param position the move position
     * @return true or false
     */
    public boolean checkBarrierBoulder(int x, int y, String position) {
        if (position.equals("up")) {
            y --;
            if (y < 0) {return false;}
        } else if (position.equals("down")) {
            y ++;
            if (y >= d.getHeight()) {return false;}
        } else if (position.equals("left")) {
            x --;
            if (x < 0) {return false;}
        } else if (position.equals("right")) {
            x ++;
            if (x >= d.getWidth()) {return false;}
        }
        Fixed f = d.findFixedByCoord(x, y);
        if (f != null) {
            Boulder b = null;
            if (f.getName().equals("Floor Switch")) {
                FloorSwitch fs = (FloorSwitch) f;
                b = fs.findBoulder(x, y);
            }
            if (f.getName().equals("Boulder") || b != null) {
                return false;
            } else {
                return f.checkPlayerBarrier(x, y, position);
            }
        }
        return true;
    }

    @Override
    public boolean checkEnemyBarrier() {
        return false;
    }
    
}