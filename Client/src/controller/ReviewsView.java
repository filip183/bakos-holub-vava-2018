package controller;

import controller.ScreenManager;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;


public class ReviewsView {
    private static Stage stage = ScreenManager.getStage();

    public ReviewsView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ScreenManager.REVIEWS));
        ScreenManager.createScene2("Okno recenzii",loader);
    }
}
