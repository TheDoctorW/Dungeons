package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        JSONObject jsonGoal = json.getJSONObject("goal-condition");

        loadGoal(dungeon, jsonGoal);

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        return dungeon;
    }

    public void loadGoal(Dungeon dungeon, JSONObject json) {
        Goal goal = getGoal(json);
        dungeon.setGoal(goal);
    }

    public Goal getGoal(JSONObject json) {
        String goalName = json.getString("goal");
        switch (goalName) {
            case "exit":
                return new ExitGoal();
            case "enemies":
                return new EnemyGoal();
            case "treasure":
                return new TreasureGoal();
            case "boulders":
                return new FloorSwitchGoal();
            case "AND":
                JSONArray subAndGoals = json.getJSONArray("subgoals");
                JSONObject subAndGoal1 = subAndGoals.getJSONObject(0);
                JSONObject subAndGoal2 = subAndGoals.getJSONObject(1);
                return new TwoAndGoal(getGoal(subAndGoal1), getGoal(subAndGoal2));
            case "OR":
                JSONArray subOrGoals = json.getJSONArray("subgoals");
                JSONObject subOrGoal1 = subOrGoals.getJSONObject(0);
                JSONObject subOrGoal2 = subOrGoals.getJSONObject(1);
                return new TwoOrGoal(getGoal(subOrGoal1), getGoal(subOrGoal2));
        }
        return new ExitGoal();
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            // entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            dungeon.addFixed(wall);
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit);
            dungeon.addFixed(exit);
            break;
        case "boulder":
            Boulder boulder = new Boulder(dungeon, x, y);
            onLoad(boulder);
            dungeon.addFixed(boulder);
            break;
        case "door":
            Door door = new Door(dungeon, x, y, dungeon.getFixedNumberByName("Door"));
            onLoad(door);
            dungeon.addFixed(door);
            break;
        case "switch":
            FloorSwitch floorSwitch = new FloorSwitch(dungeon, x, y);
            onLoad(floorSwitch);
            dungeon.addFixed(floorSwitch);
            break;
        case "portal":
            Portal portal = new Portal(dungeon, x, y, dungeon.getFixedNumberByName("Portal") / 2);
            onLoad(portal);
            dungeon.addFixed(portal);
            break;
        case "boss":
            Boss boss = new Boss(dungeon, x, y);
            onLoad(boss);
            dungeon.addEnemies(boss);
            break;
        case "soldier":
            Soldier soldier = new Soldier(dungeon, x, y);
            onLoad(soldier);
            dungeon.addEnemies(soldier);
            break;
        case "key":
            Key key = new Key(dungeon, x, y, dungeon.getCollectableNumberByName("Key"));
            onLoad(key);
            dungeon.addCollectable(key);
            break;
        case "treasure":
            Treasure treasure = new Treasure(dungeon, x, y);
            onLoad(treasure);
            dungeon.addCollectable(treasure);
            break;
        case "sword":
            Sword sword = new Sword(dungeon, x, y);
            onLoad(sword);
            dungeon.addCollectable(sword);
            break;
        case "invincibility":
            Potion potion = new Potion(dungeon, x, y);
            onLoad(potion);
            dungeon.addCollectable(potion);
            break;
        case "cure":
            Cure cure = new Cure(dungeon, x, y);
            onLoad(cure);
            dungeon.addCollectable(cure);
            break;
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Exit exit);

    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(Door door);

    public abstract void onLoad(FloorSwitch floorSwitch);

    public abstract void onLoad(Portal portal);

    public abstract void onLoad(Boss boss);

    public abstract void onLoad(Soldier soldier);

    public abstract void onLoad(Key key);

    public abstract void onLoad(Treasure treasure);

    public abstract void onLoad(Sword sword);

    public abstract void onLoad(Potion potion);

    public abstract void onLoad(Cure cure);

}
