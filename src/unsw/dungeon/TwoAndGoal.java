package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class TwoAndGoal implements Goal {
    private List<Goal> goals;
    /**
     * implement two goals are g1 g2
     * @param goal1
     * @param goal2
     */
    public TwoAndGoal(Goal goal1,Goal goal2) {
        goals = new ArrayList<Goal>();
        goals.add(goal1);
        goals.add(goal2);
    }
    /**
     * check whether has one of the goals has achieve
     */
    @Override
    public boolean checkAchieve(Dungeon d, Player p) {
        // DONE Auto-generated method stub
        for(Goal g: goals) {
            if(g.checkAchieve(d, p) == false) {
                return false;
            }
        }
        return true;
    }
    
}