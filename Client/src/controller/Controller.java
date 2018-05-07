package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.*;
import model.Movie;
import model.MovieList;
import model.User;

import javax.naming.NamingException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class Controller {
    public static  LoginView loginView;
    static Main main=new Main();
    static ResourceBundle turboWatch ;
    static int id;
    static String name;
    //login
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
    //registration
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
    //user movies
    @FXML
    public TableView<Movie> user_movies_table;
    @FXML
    public TableColumn<Movie,String> user_movies_column_name;
    @FXML
    public TableColumn<Movie,String> user_movies_column_genre;
    @FXML
    public TableColumn<Movie,String> user_movies_column_director;
    @FXML
    public TableColumn<Movie,String> user_movies_column_year;
    @FXML
    public Label user_movies_label;
    @FXML
    public TableColumn<Movie,String> user_movies_column_rating;
    @FXML
    public Button user_my_movies;
    @FXML
    public Button user_find_movies;
    @FXML
    public Button user_logout;
    @FXML
    public Label user_label;
    @FXML
    public Label user_label_name;
    @FXML
    public Button user_movies_back;


    public Controller() {
    }

    @FXML
    public void login(){
        try {
            User user = main.invokeLogin(getUserValues());
            if (user==null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                turboWatch = ResourceBundle.getBundle("turboWatch");
                alert.setTitle(turboWatch.getString("chyba"));
                alert.setHeaderText(turboWatch.getString("prihlasenieChyba"));
                alert.setContentText(turboWatch.getString("prihlasenieChybaInfo"));
                Main.logger.info( "Zle zadane meno alebo heslo");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                turboWatch = ResourceBundle.getBundle("turboWatch");
                alert.setTitle(turboWatch.getString("prihlasenie"));
                alert.setHeaderText(turboWatch.getString("prihlasenieUspesne"));
                String meno=user.getLogin();
                name=meno;
                alert.setContentText(turboWatch.getString("userPrihlaseny") + " " + meno + ".");
                Main.logger.fine("prihlaseny ako " + meno + ".");
                alert.showAndWait();
                id=user.getId();
                userWindow();

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

    @FXML
    public void userMovies(){
        new UserMoviesView();
    }
    @FXML
    public void userWindow(){
        new UserView();
    }

    @FXML
    public void logOut(){
        new LoginView();
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
        if (ScreenManager.getStage().getTitle().equals("Movies")){
            turboWatch= ResourceBundle.getBundle("turboWatch");
            System.out.println("TU SOM");
            ScreenManager.getStage().setTitle(turboWatch.getString("oknoFilmyUzivatela"));
            user_movies_column_director.setText(turboWatch.getString("umStlpecReziser"));
            user_movies_column_name.setText(turboWatch.getString("umStlpecMeno"));
            user_movies_column_genre.setText(turboWatch.getString("umStlpecZaner"));
            user_movies_column_year.setText(turboWatch.getString("umStlpecRok"));
            user_movies_label.setText(turboWatch.getString("oknoFilmyUzivatela"));
            user_movies_column_rating.setText(turboWatch.getString("umStlpecHodnotenie"));
            user_movies_back.setText(turboWatch.getString("registrationSpat"));

            try {
                MovieList movieList=main.invokeUserMovies(id);
                fillTable(movieList);
            } catch (Exception e){
                Main.logger.log(Level.WARNING,"ERROR",e.getMessage());
            }
        }
        if (ScreenManager.getStage().getTitle().equals("User")){
            turboWatch=ResourceBundle.getBundle("turboWatch");
            user_find_movies.setText(turboWatch.getString("userVyhladajFilmy"));
            user_label.setText(turboWatch.getString("userPrihlaseny"));
            user_label_name.setText(name);
            user_logout.setText(turboWatch.getString("userOdhlasenie"));
            user_my_movies.setText(turboWatch.getString("userMojeFilmy"));
        }
    }



    @FXML
    private void fillTable(MovieList movieList){
        user_movies_column_name.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
        user_movies_column_genre.setCellValueFactory(new PropertyValueFactory<Movie, String>("genre"));
        user_movies_column_director.setCellValueFactory(new PropertyValueFactory<Movie, String>("director"));
        user_movies_column_year.setCellValueFactory(new PropertyValueFactory<Movie, String>("year"));
        user_movies_column_rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        ObservableList<Movie> list= FXCollections.observableArrayList(movieList.getList());
        user_movies_table.setItems(list);
    }

    @FXML
    public void getMovieFromTable(MouseEvent event) {
        try {

            if (event.getClickCount()==2){

                Movie movie = (Movie) user_movies_table.getSelectionModel().getSelectedItem();

                System.out.println("TITUL "  + movie.getTitle()) ;

            }
        } catch (Exception e){
            Main.logger.log(Level.WARNING,"ERROR",e.getMessage());
        }
    }


}
