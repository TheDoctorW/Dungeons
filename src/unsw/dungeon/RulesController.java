package unsw.dungeon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RulesController {

    @FXML
    private Button backButton;
    private StartScreen startScreen;

    @FXML
    void handleBackButton(ActionEvent event) {
        startScreen.start();
    }

    public void setStartScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
    }
}