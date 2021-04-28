package unsw.dungeon;

public class Invincible implements PlayerStatus {
    private String status;

    public Invincible() {
        this.status = "invincible";
    }
    /**
     * change the player status to alive
     */
    @Override
    public void Alive(Player p) {
        p.setStatus(new Alive());

    }
    /**
     * change the player status to dead
     */
    @Override
    public void Dead(Player p) {
        p.setStatus(new Dead());

    }

    @Override
    public void Invincible(Player p) {

    }
    /**
     * check the status whether is same
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Invincible other = (Invincible) obj;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }

    
    
}