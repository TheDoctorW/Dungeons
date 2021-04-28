package unsw.dungeon;

public class Sword extends Collectable {

    private String name;
    private int frequency;
    private Dungeon d;
    private Player p;
    /**
     * Sword
     * @param d dungeon
     * @param x x-coord
     * @param y y-coord
     */
    public Sword(Dungeon d, int x, int y) {
        super(x, y);
        this.name = "Sword";
        this.d = d;
        this.frequency = 5;
        this.p = d.getPlayer();
    }

    @Override
    public int getid() {
        return 0;
    }
    /**
     * getter of the Sword name
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * collect the sword
     */
    @Override
    public void collectItem() {
        if(p.checkBag("Sword") == null) {
            p.addBagList(this);
            this.visibility().set(false);
            d.removeCollectable(this);
        }
        else {
            return;
        }
    }
    /**
     * getter the sword frequency
     */
    public int getFrequency() {
        return frequency;
    }
    /**
     * setter the sword frequency
     * @param frequency
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    
}