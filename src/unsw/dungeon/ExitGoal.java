package unsw.dungeon;

public class ExitGoal implements Goal {
    /**
     * check the exit goal whether is achieve
     */
    @Override
    public boolean checkAchieve(Dungeon d, Player p) {
        // DONE Auto-generated method stub
        for(Fixed f: d.getFixList()) {
            if(f.getName().equals("Exit")) {
                if((f.getX() == p.getX()) && (f.getY() == p.getY())) {
                    return true;
                }
            }
        }
        return false;
    }
    
}