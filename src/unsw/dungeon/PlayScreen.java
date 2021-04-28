package unsw.dungeon;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlayScreen {
    private Stage stage;
    private String title;
    private PlayController controller;
    private Scene scene;

    public PlayScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Dungenon Game";

        controller = new PlayController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Play.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root, 600, 400);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public PlayController getController() {
        return controller;
    }

    public Stage getStage() {
        return stage;
    }
}