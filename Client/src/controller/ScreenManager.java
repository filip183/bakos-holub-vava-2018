package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Main;

public class ScreenManager {

    private static Stage stage;
    public static String LOGIN = "/Login.fxml";
    public static String REGISTRATION = "/Registration.fxml";


    public ScreenManager(Stage stage) {
        ScreenManager.stage = stage;
    }

    public static void setStage(Stage stage) {
        ScreenManager.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void createScene(String sceneName,FXMLLoader loader) {
        try {
            stage.setTitle(sceneName);
            stage.setScene(new Scene(loader.load()));
            stage.show();

        } catch (Exception e) {
            Main.logger.severe("Chyba pri vytvoreni sceny"+sceneName+" \n"+ e.getMessage());
        }

    }

}
