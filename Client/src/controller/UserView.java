package controller;

import controller.ScreenManager;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;


public class UserView {
    private static Stage stage = ScreenManager.getStage();

    public UserView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ScreenManager.USER_VIEW));
        ScreenManager.createScene("User",loader);
    }
}