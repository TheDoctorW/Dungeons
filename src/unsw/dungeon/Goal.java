package unsw.dungeon;

public interface Goal {
    /**
     * composite pattern
     * @param d dungeon
     * @param p player
     * @return check the goal whether has achieve
     */
    public boolean checkAchieve(Dungeon d,Player p);
}