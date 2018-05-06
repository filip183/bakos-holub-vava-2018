package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginView extends Stage {

    public LoginView() throws Exception{
        super();
        Stage primaryStage=new Stage();
        Parent okno = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(okno, 600, 400));
        primaryStage.show();



    }

}
