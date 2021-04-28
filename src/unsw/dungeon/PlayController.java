package unsw.dungeon;

import javafx.fxml.FXML;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PlayController {
    @FXML
    private Button easy;
    @FXML
    private Button medium;
    @FXML
    private Button hard;
    @FXML
    private StartScreen startScreen;
    @FXML
    private PlayScreen playScreen;
    @FXML
    private Button backButton;
    
    @FXML
    void hundleEasyButton(ActionEvent event) throws Exception {
        DungeonScreen dungeonScreen = new DungeonScreen(playScreen.getStage(),"easy.json");
        dungeonScreen.start();
    }

    @FXML
    void hundleMediumButton(ActionEvent event) throws Exception {
        DungeonScreen dungeonScreen = new DungeonScreen(playScreen.getStage(),"medium.json");
        dungeonScreen.start();
    }

    @FXML
    void hundleHardButton(ActionEvent event) throws Exception {
        DungeonScreen dungeonScreen = new DungeonScreen(playScreen.getStage(), "hard.json");
        dungeonScreen.start();
    }

    @FXML
    void handleBackButton(ActionEvent event) {
        startScreen.start();
    }

    public void setStartScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
    }

    public void setPlayerScreen(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

}