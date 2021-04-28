package unsw.dungeon;

public class Boss extends Enemy {

    private String name;

    public Boss(Dungeon d, int x, int y) {
        super(d, x, y);
        this.name = "Boss";
    }

    @Override
    public String getName() {
        return this.name;
    }
    
}