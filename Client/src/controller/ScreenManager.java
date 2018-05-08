package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.BeanInvoker;

public class ScreenManager {

    private static Stage stage;
    private static Stage stage2;
    public static String LOGIN = "/Login.fxml";
    public static String REGISTRATION = "/Registration.fxml";
    public static String USER_MOVIES = "/UserMovies.fxml";
    public static String USER_VIEW = "/User.fxml";
    public static String FILM_DETAIL ="/FilmDetail.fxml";
    public static String FILTER_MOVIES ="/SearchFilters.fxml";
    public static String ALL_MOVIES ="/UserMovies.fxml";
    public static String REVIEWS="/Reviews.fxml";

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

    public static void createScene2(String sceneName,FXMLLoader loader) {
        try {
            stage2.setTitle(sceneName);
            stage2.setScene(new Scene(loader.load()));
            stage2.show();

        } catch (Exception e) {
            BeanInvoker.logger.severe("Chyba pri vytvoreni sceny "+sceneName+" \n"+ e.getMessage());
            e.printStackTrace();
        }

    }

    public static Stage getStage2() {
        return stage2;
    }

    public static void setStage2(Stage stage2) {
        ScreenManager.stage2 = stage2;
    }
}
