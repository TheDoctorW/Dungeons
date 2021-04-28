package unsw.dungeon;

public class FloorSwitchGoal implements Goal {
    /**
     * check the Floorswitch goal whether has achieve
     */
    @Override
    public boolean checkAchieve(Dungeon d, Player p) {
        // DONE Auto-generated method stub
        for(Fixed f: d.getFixList()) {
            if(f.getName().equals("Floor Switch")) {
                FloorSwitch fs = (FloorSwitch) f;
                if(fs.getState().equals("normal")) {
                    return false;
                }
            }
        }
        return true;
    }
    
}