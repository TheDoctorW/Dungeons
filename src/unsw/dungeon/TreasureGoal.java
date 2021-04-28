package unsw.dungeon;

public class TreasureGoal implements Goal {

    /**
     * check the dungeon whether still has treasure
     * after collect all the treasure
     * the dungeon should not have treasure
     * achieve the goal
     */
    @Override
    public boolean checkAchieve(Dungeon d, Player p) {
        // DONE Auto-generated method stub
        for(Collectable c: d.getCollectableList()) {
            if(c.getName().equals("Treasure")) {
                return false;
            }
        }
        return true;
    }
    
}