package unsw.dungeon;

public class Cure extends Collectable {
    
    private String name;
    private Dungeon d;
    private Player p;
    /**
     * Potion
     * @param d dungeon
     * @param x x-coord
     * @param y y-coord
     */
    public Cure(Dungeon d, int x, int y) {
        super(x, y);
        this.name = "Cure";
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
        usingCure();
    }
    /**
     * using the cure
     * player will change the status to invincible 2s
     */
    public void usingCure() {
        int newLife = p.getLifes();
        newLife ++;
        if (newLife > 3) {newLife = 3;}
        p.setLifes(newLife);
    }

}