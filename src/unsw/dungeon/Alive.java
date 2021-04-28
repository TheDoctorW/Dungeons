package unsw.dungeon;

public class Alive implements PlayerStatus {
    private String status;
    /**
     * the state of player is alive
     * @param p
     */
    public Alive() {
        status = "alive";
    }

    @Override
    public void Alive(Player p) {

    }
    /**
     * transfer the state of player to dead 
     */
    @Override
    public void Dead(Player p) {
        p.setStatus(new Dead());

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
        Alive other = (Alive) obj;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }

    

    
    
}