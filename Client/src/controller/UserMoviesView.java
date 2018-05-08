package controller;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;


public class UserMoviesView {
    private static Stage stage = ScreenManager.getStage();

    public UserMoviesView(int i) {
        if(i==0){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ScreenManager.USER_MOVIES));
            ScreenManager.createScene("Movies",loader);
        } else if (i==1){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ScreenManager.ALL_MOVIES));
            ScreenManager.createScene("MoviesByFilter",loader);
        }
    }


}
