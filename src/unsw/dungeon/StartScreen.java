package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartScreen {
    private Stage stage;
    private String title;
    private StartController controller;
    private Scene scene;

    public StartScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Dungenon Game";

        controller = new StartController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
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

    public StartController getController() {
        return controller;
    }

    public Stage getStage() {
        return stage;
    }

}
