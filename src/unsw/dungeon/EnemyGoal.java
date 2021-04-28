package unsw.dungeon;

public class EnemyGoal implements Goal {

    @Override
    public boolean checkAchieve(Dungeon d, Player p) {
        if(d.getEnemies().size() != 0) {
            return false;
        }
        return true;
    }
    
}