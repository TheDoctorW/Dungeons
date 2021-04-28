package unsw.dungeon;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameOverScreen {
    private Stage stage;
    private String title;
    private Scene scene;

    public GameOverScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Dungenon Game";

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOver.fxml"));
        Parent root = loader.load();
        scene = new Scene(root, 600, 400);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

}