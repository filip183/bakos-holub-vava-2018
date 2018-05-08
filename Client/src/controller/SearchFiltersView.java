package controller;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class SearchFiltersView {

    private static Stage stage = ScreenManager.getStage();

    public SearchFiltersView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ScreenManager.FILTER_MOVIES));
        ScreenManager.createScene("Filter",loader);
    }
}
