package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RulesScreen {
    private Stage stage;
    private String title;
    private RulesController controller;
    private Scene scene;

    public RulesScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Dungenon Game";

        controller = new RulesController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("rules.fxml"));
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

    public RulesController getController() {
        return controller;
    }

    public Stage getStage() {
        return stage;
    }

}
