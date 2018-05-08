package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.BeanInvoker;

public class ScreenManager {

    private static Stage stage;
    public static String LOGIN = "/Login.fxml";
    public static String REGISTRATION = "/Registration.fxml";
    public static String USER_MOVIES = "/UserMovies.fxml";
    public static String USER_VIEW = "/User.fxml";
    public static String FILMDETAIL ="/FilmDetail.fxml";
    public static String FILTERMOVIES ="/SearchFilters.fxml";
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
            BeanInvoker.logger.severe("Chyba pri vytvoreni sceny"+sceneName+" \n"+ e.getMessage());
            e.printStackTrace();
        }

    }

}
