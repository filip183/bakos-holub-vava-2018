package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.*;
import model.User;

import java.util.Locale;
import java.util.ResourceBundle;

public class Controller {
    public static  LoginView loginView;
    static Main main=new Main();
    static ResourceBundle turboWatch ;

    @FXML
    public Button login_en;
    @FXML
    public Label login_lang;
    @FXML
    public Button login_sk;
    @FXML
    public Label login_user_nameI;
    @FXML
    public Label login_pass;
    @FXML
    public TextField login_user_name;
    @FXML
    public TextField login_user_pass;
    @FXML
    public Button login_login;
    @FXML
    public Button login_registation;


    public Controller() {
    }

    @FXML
    public void login(){
        try {
            User user = main.invokeLogin(getUserValues());
            if (user==null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Chyba");
                alert.setHeaderText("Chyba pri prihlaseni");
                alert.setContentText("Zle zadane meno alebo heslo");

                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Prihlasenie");
                alert.setHeaderText("Uspesne prihlasenie");
                alert.setContentText("Ste prihlaseny ako " + user.getLogin() + ".");

                alert.showAndWait();

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public User getUserValues(){
        User user = new User(login_user_name.getText(),login_user_pass.getText());
        return user;
    }

    @FXML
    public void changeLangLoginEN(){
        Locale.setDefault(new Locale("en", "US"));
        turboWatch = ResourceBundle.getBundle("turboWatch");
        login_lang.setText(turboWatch.getString("loginJazyk"));
        login_user_nameI.setText(turboWatch.getString("loginPrihlasovacieMeno"));
        login_pass.setText(turboWatch.getString("loginHeslo"));
        login_login.setText(turboWatch.getString("loginPrihlasit"));
        login_registation.setText(turboWatch.getString("loginRegistrovat"));
    }

    @FXML
    public void changeLangLoginSK(){
        Locale.setDefault(new Locale("sk", "SK"));
        turboWatch = ResourceBundle.getBundle("turboWatch");
        login_lang.setText(turboWatch.getString("loginJazyk"));
        login_user_nameI.setText(turboWatch.getString("loginPrihlasovacieMeno"));
        login_pass.setText(turboWatch.getString("loginHeslo"));
        login_login.setText(turboWatch.getString("loginPrihlasit"));
        login_registation.setText(turboWatch.getString("loginRegistrovat"));


    }




}
