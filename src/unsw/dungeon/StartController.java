package unsw.dungeon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {

    @FXML
    private Button startButton;
    private Button rulesButton;
    private PlayScreen playerScreen;
    private RulesScreen rulesScreen;

    @FXML
    void handleStartButton(ActionEvent event) {
        playerScreen.start();
    }

    @FXML
    void handleRulesButton(ActionEvent event) {
        rulesScreen.start();
    }

    public void setPlayerScreen(PlayScreen playerscreen) {
        this.playerScreen = playerscreen;
    }

    public void setRulesScreen(RulesScreen rulesScreen) {
        this.rulesScreen = rulesScreen;
    }
}