package main;

import controller.Controller;
import controller.LoginView;
import controller.ScreenManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.FileHandler;


public class View extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ScreenManager.setStage(primaryStage);
        Controller.loginView = new LoginView();
        try {
            FileHandler fh = new FileHandler("resources/logFile.txt", true);
            BeanInvoker.logger.addHandler(fh);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
