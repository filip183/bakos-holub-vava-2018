package controller;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;


public class UserMoviesView {
    private static Stage stage = ScreenManager.getStage();

    public UserMoviesView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ScreenManager.USER_MOVIES));
        ScreenManager.createScene("Movies",loader);
    }
}
