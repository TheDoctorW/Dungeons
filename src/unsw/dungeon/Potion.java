package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

public class Potion extends Collectable {

    private String name;
    private Dungeon d;
    private Player p;
    /**
     * Potion
     * @param d dungeon
     * @param x x-coord
     * @param y y-coord
     */
    public Potion(Dungeon d, int x, int y) {
        super(x, y);
        this.name = "Potion";
        this.d = d;
        this.p = d.getPlayer();
    }

    @Override
    public int getid() {
        return 0;
    }
    /**
     * getter of the potion name
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * collect the item
     */
    @Override
    public void collectItem() {
        this.visibility().set(false);
        d.removeCollectable(this);
        usingPotion();
    }
    /**
     * using the potion
     * player will change the status to invincible 2s
     */
    public void usingPotion() {
        p.getStatus().Invincible(p);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                p.setStatus(new Alive());
			}
        };
        timer.schedule(task, 10000);
    }
}