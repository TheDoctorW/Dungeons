package unsw.dungeon;

public class Soldier extends Enemy {

    private String name;

    public Soldier(Dungeon d, int x, int y) {
        super(d, x, y);
        this.name = "Soldier";
    }

    @Override
    public String getName() {
        return this.name;
    }
    
}