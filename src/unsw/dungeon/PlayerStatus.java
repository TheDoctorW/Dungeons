package unsw.dungeon;
/**
 * State Pattern
 * the interface of the player state
 */
public interface PlayerStatus {
    
    public void Alive(Player p);
    public void Dead(Player p);
    public void Invincible(Player p);

}