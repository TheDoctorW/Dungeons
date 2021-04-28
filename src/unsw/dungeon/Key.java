package unsw.dungeon;

public class Key extends Collectable {

    private int id;
    private String name;
    private Dungeon d;
    private Player p;
    /**
     * 
     * @param d dungeon
     * @param x x-coord
     * @param y y-coord
     * @param id the key of id
     */
    public Key(Dungeon d, int x, int y, int id) {
        super(x, y);
        this.id = id;
        this.name = "Key";
        this.d = d;
        this.p = d.getPlayer();
    }

    /**
     * return id
     */
    @Override
    public int getid() {
        return id;
    }
    /**
     * return name
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * collect item
     */
    @Override
    public void collectItem() {
        if(p.checkBag("Key") == null) {
            p.addBagList(this);
            this.visibility().set(false);
            d.removeCollectable(this);
        }
        else {
            return;
        }
    }

    
}