package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.*;
import model.User;

import javax.naming.NamingException;
import java.sql.Date;
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
    @FXML
    public Button registration_login_button;
    @FXML
    public PasswordField registration_password_field;
    @FXML
    public PasswordField registration_password2_field;
    @FXML
    public TextField registration_login_field;
    @FXML
    public TextField registration_email_field;
    @FXML
    public Label registration_email_label;
    @FXML
    public Label registration_login_label;
    @FXML
    public Label registration_password_label;
    @FXML
    public Label registration_password2_label;
    @FXML
    public Button registration_back_button;

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
                Main.logger.info( "Zle zadane meno alebo heslo");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Prihlasenie");
                alert.setHeaderText("Uspesne prihlasenie");
                String meno=user.getLogin();
                alert.setContentText("Ste prihlaseny ako " + meno + ".");
                Main.logger.fine("prihlaseny ako " + meno + ".");
                alert.showAndWait();

            }
        }
        catch (Exception e){
            e.printStackTrace();
            Main.logger.severe("Chyba pri vytvoreni prvej sceny \n"+ e.getMessage());
        }
    }

    @FXML
    public User getUserValues(){
        User user = new User(login_user_name.getText(),login_user_pass.getText());
        return user;
    }

    @FXML
    public void changeLangLoginEN(){
        Main.logger.fine("Jazyk zmeneny na Anglictinu");
        Locale.setDefault(new Locale("en", "US"));
        initialize();
    }

    @FXML
    public void changeLangLoginSK(){
        Main.logger.fine("Jazyk zmeneny na Slovencinu");
        Locale.setDefault(new Locale("sk", "SK"));
        initialize();
    }

    @FXML
    public void registration(){
        new RegistrationView();
    }

    @FXML
    public void registrationBack(){
        new LoginView();
    }

    @FXML
    public void registrater(){
        User user =new User(registration_login_field.getText(),registration_password_field.getText(),registration_email_field.getText());
        try {
            if (registration_password2_field.getText().equals(user.getPassword())) {
                if (main.invokeRegistration(user)==1){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Registracia");
                    alert.setHeaderText("Registracia uspesna");
                    String meno=user.getLogin();
                    Main.logger.fine("Registracia " + meno + ".");
                    alert.showAndWait();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Registracia");
                    String meno=user.getLogin();
                    alert.setHeaderText("Pouzivatel "+meno+" existuje");
                    Main.logger.fine("Registracia " + meno + ".");
                    alert.showAndWait();
                }
            }

            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Chyba");
                alert.setHeaderText("Heslo sa nezhoduje");
                alert.setContentText("Heslo sa nezhoduje");
                Main.logger.info("Heslo sa nezhoduje");
                alert.showAndWait();
            }
        }
        catch (NamingException e){
            Main.logger.severe("Naming Exception "+ e.getStackTrace());
        }
    }

    public void initialize(){
        if (ScreenManager.getStage().getTitle().equals("Registracne okno")){
            turboWatch = ResourceBundle.getBundle("turboWatch");
            ScreenManager.getStage().setTitle(turboWatch.getString("oknoRegistracia"));
            registration_login_label.setText(turboWatch.getString("registrationPrihlasovacieMeno"));
            registration_password2_label.setText(turboWatch.getString("registrationOverenieHesla"));
            registration_password_label.setText(turboWatch.getString("registrationHeslo"));
            registration_login_button.setText(turboWatch.getString("registrationRegistruj"));
            registration_back_button.setText(turboWatch.getString("registrationSpat"));
        }
        if (ScreenManager.getStage().getTitle().equals("Prihlasenie") || ScreenManager.getStage().getTitle().equals("Login")){
            turboWatch = ResourceBundle.getBundle("turboWatch");
            ScreenManager.getStage().setTitle(turboWatch.getString("oknoLogin"));
            login_lang.setText(turboWatch.getString("loginJazyk"));
            login_user_nameI.setText(turboWatch.getString("loginPrihlasovacieMeno"));
            login_pass.setText(turboWatch.getString("loginHeslo"));
            login_login.setText(turboWatch.getString("loginPrihlasit"));
            login_registation.setText(turboWatch.getString("loginRegistrovat"));
        }
    }

}
