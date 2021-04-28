package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class TwoOrGoal implements Goal {
    private List<Goal> goals;
    /**
     * implement two goals 
     * the condition is g1 or g2
     * @param goal1
     * @param goal2
     */
    public TwoOrGoal(Goal goal1, Goal goal2) {
        goals = new ArrayList<Goal>();
        goals.add(goal1);
        goals.add(goal2);
    }
    /**
     * check the goal whether achieve
     */
    @Override
    public boolean checkAchieve(Dungeon d, Player p) {
        // DONE Auto-generated method stub
        for(Goal g: goals) {
            if(g.checkAchieve(d, p) == true) {
                return true;
            }
        }
        return false;
    }

    
}