package main;

import controller.Controller;
import controller.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;


public class View extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Controller.loginView = new LoginView();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
