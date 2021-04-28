package unsw.dungeon;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScreen {
    private Stage stage;
    private String title;
    private Scene scene;
    private DungeonController controller;
    private String fileName;
    
    public DungeonScreen(Stage stage, String fileName) throws Exception {
        this.stage = stage;
        title = "Dungeon Game";
        this.fileName = fileName;
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(fileName);

        DungeonController controller = dungeonLoader.loadController(this);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
    }
    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public DungeonController getController() {
        return controller;
    }  

    public Stage getStage() {
        return stage;
    }
}