package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    @FXML
    private DungeonScreen dungeonScreen;
    private GameOverScreen gameOverScreen;
    private PlayerDieScreen playerDieScreen;
    
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, DungeonScreen dungeonScreen) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.dungeonScreen = dungeonScreen;

        try {
            this.gameOverScreen = new GameOverScreen(dungeonScreen.getStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        dungeon.goalState().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue.booleanValue()) {
                    // for (ImageView entity : initialEntities) {
                    //     entity.setVisible(false);
                    // }
                    gameOverScreen.start();
                }
            }
        });

        try {
            this.playerDieScreen = new PlayerDieScreen(dungeonScreen.getStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        dungeon.playerState().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue.booleanValue()) {
                    // for (ImageView entity : initialEntities) {
                    //     entity.setVisible(false);
                    // }
                    playerDieScreen.start();
                }
            }
        });
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities) {
            squares.getChildren().add(entity);
        }
        
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        default:
            break;
        }
    }

}

