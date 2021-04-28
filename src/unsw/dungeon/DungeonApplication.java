package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        StartScreen startScreen = new StartScreen(primaryStage);
        RulesScreen rulesScreen = new RulesScreen(primaryStage);
        PlayScreen playScreen = new PlayScreen(primaryStage);
        startScreen.getController().setRulesScreen(rulesScreen);
        rulesScreen.getController().setStartScreen(startScreen);
        startScreen.getController().setPlayerScreen(playScreen);
        playScreen.getController().setStartScreen(startScreen);
        playScreen.getController().setPlayerScreen(playScreen);
        startScreen.start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
