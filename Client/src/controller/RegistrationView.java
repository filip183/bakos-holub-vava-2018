package controller;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;


public class RegistrationView {
    private static Stage stage = ScreenManager.getStage();

    public RegistrationView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ScreenManager.REGISTRATION));
        ScreenManager.createScene("Registracne okno",loader);
    }
}
