package controller;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class LoginView {
    private static Stage stage = ScreenManager.getStage();

    public LoginView() {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ScreenManager.LOGIN));
            ScreenManager.createScene("Prihlasenie",loader);
    }

}
