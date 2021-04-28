package unsw.dungeon;

public class Dead implements PlayerStatus {
    private String status;
    /**
     * the state of player dead
     */
    public Dead() {

    }
    /**
     * transfer the state of player to alive
     */
    @Override
    public void Alive(Player p) {
        p.setStatus(new Alive());

    }

    @Override
    public void Dead(Player p) {

    }
    /**
     * transfer the state of player to invincible
     */
    @Override
    public void Invincible(Player p) {
        p.setStatus(new Invincible());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Dead other = (Dead) obj;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }

    

    
}