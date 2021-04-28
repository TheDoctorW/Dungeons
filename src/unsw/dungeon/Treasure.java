package unsw.dungeon;

public class Treasure extends Collectable {

    private String name;
    private Dungeon d;
    private Player p;
    /**
     * Treasure
     * @param d dungeon
     * @param x x-coord
     * @param y y-coord
     */
    public Treasure(Dungeon d, int x, int y) {
        super(x, y);
        this.name = "Treasure";
        this.d = d;
        this.p = d.getPlayer();
    }

    @Override
    public int getid() {
        return 0;
    }
    /**
     * getter of the treasure name
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * collect the treasure
     * then remove the treasure in the dungeon
     */
    @Override
    public void collectItem() {
        p.addBagList(this);
        this.visibility().set(false);
        d.removeCollectable(this);
    }

}