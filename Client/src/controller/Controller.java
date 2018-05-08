package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.*;
import model.*;

import javax.naming.NamingException;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class Controller {
    public static  LoginView loginView;
    static BeanInvoker beanInvoker =new BeanInvoker();
    static ResourceBundle turboWatch ;
    static int id, checkNumber;
    static String name;
    static Movie movie;
    static String filterGlob;
    static Review review;
    //login

    @FXML
    public Label login_lang;
    @FXML
    public Button login_lang_change;
    @FXML
    public Label login_user_nameI;
    @FXML
    public Label login_pass;
    @FXML
    public TextField login_user_name;
    @FXML
    public PasswordField login_user_pass;
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
    @FXML
    public Label filmDetail_title_label;
    @FXML
    public Label filmDetail_title_field;
    @FXML
    public Label filmDetail_genre_label;
    @FXML
    public Label filmDetail_genre_field;
    @FXML
    public Label filmDetail_year_label;
    @FXML
    public Label filmDetail_year_field;
    @FXML
    public Label filmDetail_director_label;
    @FXML
    public Label filmDetail_director_field;
    @FXML
    public Label filmDetail_actors_label;
    @FXML
    public ComboBox<String> filmDetail_actors_field;
    @FXML
    public Label filmDetail_rating_label;
    @FXML
    public Label filmDetail_rating_field;
    @FXML
    public Label filmDetail_myRating_label;
    @FXML
    public TextField filmDetail_myRating_field;
    @FXML
    public Label filmDetail_ratingDetail_label;
    @FXML
    public TextField filmDetail_ratingDetail_field;
    @FXML
    public Button filmDetail_rate_button;
    @FXML
    public TextField searchFilters_title_field;
    @FXML
    public TextField searchFilters_genre_field;
    @FXML
    public TextField searchFilters_year_from_field;
    @FXML
    public TextField searchFilters_year_to_field;
    @FXML
    public TextField searchFilters_director_field;
    @FXML
    public TextField searchFilters_actors_field;
    @FXML
    public TextField searchFilters_rating_field;
    @FXML
    public Label searchFilters_title_label;
    @FXML
    public Label searchFilters_genre_label;
    @FXML
    public Label searchFilters_year_label;
    @FXML
    public Label searchFilters_director_label;
    @FXML
    public Label searchFilters_actors_label;
    @FXML
    public Label searchFilters_rating_label;
    @FXML
    public Label searchFilters_year_from_label;
    @FXML
    public Label searchFilters_year_to_label;
    @FXML
    public Button searchFilters_search_button;
    @FXML
    public Button searchFilters_spat_button;
    @FXML
    public ImageView login_logo;
    @FXML
    public ComboBox<String> language;
    @FXML
    public Label movie_reviews_all_label;
    @FXML
    public TableView reviews_table;
    @FXML
    public TableColumn<Review, String> review_user;
    @FXML
    public TableColumn<Review, String> review_rating;
    @FXML
    public Label review_detail_label;
    @FXML
    public TextArea review_detail;


    public Controller() {
    }

    @FXML
    public void login(){
        try {
            User user = beanInvoker.invokeLogin(getUserValues());
            if (user==null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                turboWatch = ResourceBundle.getBundle("turboWatch");
                alert.setTitle(turboWatch.getString("chyba"));
                alert.setHeaderText(turboWatch.getString("prihlasenieChyba"));
                alert.setContentText(turboWatch.getString("prihlasenieChybaInfo"));
                BeanInvoker.logger.info( "Zle zadane meno alebo heslo");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                turboWatch = ResourceBundle.getBundle("turboWatch");
                alert.setTitle(turboWatch.getString("prihlasenie"));
                alert.setHeaderText(turboWatch.getString("prihlasenieUspesne"));
                String meno=user.getLogin();
                name=meno;
                alert.setContentText(turboWatch.getString("userPrihlaseny") + " " + meno + ".");
                BeanInvoker.logger.fine("prihlaseny ako " + meno + ".");
                alert.showAndWait();
                id=user.getId();
                userWindow();

            }
        }
        catch (Exception e){
            e.printStackTrace();
            BeanInvoker.logger.severe("Chyba pri vytvoreni prvej sceny \n"+ e.getMessage());
        }
    }

    @FXML
    public User getUserValues(){
        User user = new User(login_user_name.getText(),login_user_pass.getText());
        return user;
    }

    @FXML
    public void changeLangLogin(){
        String lang = language.getValue();
        if(lang!=null){
            if(lang.equals("Slovencina")) Locale.setDefault(new Locale("sk", "SK"));
            if(lang.equals("English")) Locale.setDefault(new Locale("en", "US"));
            if(lang.equals("Espanol")) Locale.setDefault(new Locale("es", "ES"));
            if(lang.equals("Italian")) Locale.setDefault(new Locale("it", "IT"));
            if(lang.equals("Islenska")) Locale.setDefault(new Locale("is", "IS"));
            initialize();
        }

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
                if (beanInvoker.invokeRegistration(user)==1){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Registracia");
                    alert.setHeaderText("Registracia uspesna");
                    String meno=user.getLogin();
                    BeanInvoker.logger.fine("Registracia " + meno + ".");
                    alert.showAndWait();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Registracia");
                    String meno=user.getLogin();
                    alert.setHeaderText("Pouzivatel "+meno+" existuje");
                    BeanInvoker.logger.fine("Registracia " + meno + ".");
                    alert.showAndWait();
                }
            }

            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Chyba");
                alert.setHeaderText("Heslo sa nezhoduje");
                alert.setContentText("Heslo sa nezhoduje");
                BeanInvoker.logger.info("Heslo sa nezhoduje");
                alert.showAndWait();
            }
        }
        catch (NamingException e){
            BeanInvoker.logger.severe("Naming Exception "+ e.getStackTrace());
            e.printStackTrace();
        }
    }

    @FXML
    public void userMovies(){
        checkNumber=0;
        new UserMoviesView(0);
    }

    @FXML
    public void allMovies(){
        checkNumber=1;
        new UserMoviesView(1);
    }

    @FXML
    public void userWindow(){
        new UserView();
    }

    @FXML
    public void reviewsWindow(){
        new ReviewsView();
    }

    @FXML
    public void logOut(){
        new LoginView();
    }

    public ObservableList<String> setList(){
        ObservableList<String> languageList = FXCollections.observableArrayList();
        languageList.add("Slovencina");
        languageList.add("English");
        languageList.add("Espanol");
        languageList.add("Italian");
        languageList.add("Islenska");
        return languageList;
    }

    public void initialize(){
        if(ScreenManager.getStage()==null){
            System.out.println("RRRRPRPRPRPRPRPRPPR");
        }
        if (ScreenManager.getStage().getTitle().equals("Registracne okno")){
            turboWatch = ResourceBundle.getBundle("turboWatch");
            ScreenManager.getStage().setTitle(turboWatch.getString("oknoRegistracia"));
            registration_login_label.setText(turboWatch.getString("registrationPrihlasovacieMeno"));
            registration_password2_label.setText(turboWatch.getString("registrationOverenieHesla"));
            registration_password_label.setText(turboWatch.getString("registrationHeslo"));
            registration_login_button.setText(turboWatch.getString("registrationRegistruj"));
            registration_back_button.setText(turboWatch.getString("registrationSpat"));

        } else if (ScreenManager.getStage().getTitle().equals("Prihlasenie")){
            turboWatch = ResourceBundle.getBundle("turboWatch");
            //ScreenManager.getStage().setTitle(turboWatch.getString("oknoLogin"));
            login_lang.setText(turboWatch.getString("loginJazyk"));
            login_user_nameI.setText(turboWatch.getString("loginPrihlasovacieMeno"));
            login_pass.setText(turboWatch.getString("loginHeslo"));
            login_login.setText(turboWatch.getString("loginPrihlasit"));
            login_registation.setText(turboWatch.getString("loginRegistrovat"));
            language.getItems().setAll(setList());
            try {
                login_logo.setImage(new Image(new FileInputStream("resources/Logo.JPG")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (ScreenManager.getStage().getTitle().equals("Movies")){
            turboWatch= ResourceBundle.getBundle("turboWatch");
            ScreenManager.getStage().setTitle(turboWatch.getString("oknoFilmyUzivatela"));
            user_movies_column_director.setText(turboWatch.getString("umStlpecReziser"));
            user_movies_column_name.setText(turboWatch.getString("umStlpecMeno"));
            user_movies_column_genre.setText(turboWatch.getString("umStlpecZaner"));
            user_movies_column_year.setText(turboWatch.getString("umStlpecRok"));
            user_movies_label.setText(turboWatch.getString("oknoFilmyUzivatela"));
            user_movies_column_rating.setText(turboWatch.getString("umStlpecHodnotenie"));
            user_movies_back.setText(turboWatch.getString("registrationSpat"));

            try {
                MovieList movieList= beanInvoker.invokeUserMovies(id);
                fillTable(movieList);
            } catch (Exception e){
                BeanInvoker.logger.log(Level.WARNING,"ERROR",e.getMessage());
                e.printStackTrace();
            }
        } else if (ScreenManager.getStage().getTitle().equals("User")){
            turboWatch=ResourceBundle.getBundle("turboWatch");
            user_find_movies.setText(turboWatch.getString("userVyhladajFilmy"));
            user_label.setText(turboWatch.getString("userPrihlaseny"));
            user_label_name.setText(name);
            user_logout.setText(turboWatch.getString("userOdhlasenie"));
            user_my_movies.setText(turboWatch.getString("userMojeFilmy"));
        } else if (filmDetail_director_label!=null){
            turboWatch=ResourceBundle.getBundle("turboWatch");
            filmDetail_director_label.setText(turboWatch.getString("umStlpecReziser"));
            filmDetail_actors_label.setText(turboWatch.getString("actors"));
            filmDetail_genre_label.setText(turboWatch.getString("umStlpecZaner"));
            filmDetail_myRating_label.setText(turboWatch.getString("MojeHodnotenie"));
            filmDetail_rate_button.setText(turboWatch.getString("ohodnot"));
            filmDetail_ratingDetail_label.setText(turboWatch.getString("DetailHodnotenia"));
            filmDetail_year_label.setText(turboWatch.getString("umStlpecRok"));
            filmDetail_rating_label.setText(turboWatch.getString("umStlpecHodnotenie"));
            filmDetail_title_label.setText(turboWatch.getString("umStlpecMeno"));
            insertMovieDetail();
        } else if(ScreenManager.getStage().getTitle().equals("Filter")){
            turboWatch=ResourceBundle.getBundle("turboWatch");
            searchFilters_director_label.setText(turboWatch.getString("umStlpecReziser"));
            searchFilters_actors_label.setText(turboWatch.getString("actors"));
            searchFilters_genre_label.setText(turboWatch.getString("umStlpecZaner"));
            searchFilters_rating_label.setText(turboWatch.getString("umStlpecHodnotenie"));
            searchFilters_title_label.setText(turboWatch.getString("umStlpecMeno"));
            searchFilters_year_label.setText(turboWatch.getString("umStlpecRok"));
            searchFilters_year_from_label.setText(turboWatch.getString("od"));
            searchFilters_year_to_label.setText(turboWatch.getString("do"));
            searchFilters_search_button.setText(turboWatch.getString("Hladaj"));
            searchFilters_spat_button.setText(turboWatch.getString("registrationSpat"));
        } else if (ScreenManager.getStage().getTitle().equals("MoviesByFilter")){
            turboWatch= ResourceBundle.getBundle("turboWatch");
            ScreenManager.getStage().setTitle(turboWatch.getString("vyhladaneFilmy"));
            user_movies_column_director.setText(turboWatch.getString("umStlpecReziser"));
            user_movies_column_name.setText(turboWatch.getString("umStlpecMeno"));
            user_movies_column_genre.setText(turboWatch.getString("umStlpecZaner"));
            user_movies_column_year.setText(turboWatch.getString("umStlpecRok"));
            user_movies_label.setText(turboWatch.getString("vyhladaneFilmy"));
            user_movies_column_rating.setText(turboWatch.getString("umStlpecHodnotenie"));
            user_movies_back.setText(turboWatch.getString("registrationSpat"));
            MovieList movieList = BeanInvoker.invokeRemoteMovieSearchBean(filterGlob);
            fillTable(movieList);
        } else if (ScreenManager.getStage2().getTitle().equals("Okno recenzii")){
            turboWatch= ResourceBundle.getBundle("turboWatch");
            review_rating.setText(turboWatch.getString("umStlpecHodnotenie"));
            review_user.setText(turboWatch.getString("uzivatel"));
            movie_reviews_all_label.setText(turboWatch.getString("recenzie"));
            review_detail_label.setText(turboWatch.getString("detail"));
            ReviewList reviewList = BeanInvoker.invokeRemoteReviewBean(movie.getTitle());
            fillTableReviews(reviewList);
        }


    }

    @FXML
    private void fillTableReviews(ReviewList reviewList){
        //System.out.println("REVIEW " + reviewList.getReviews().get(0).getUserName());
        review_user.setCellValueFactory(new PropertyValueFactory<Review, String>("userName"));
        review_rating.setCellValueFactory(new PropertyValueFactory<Review, String>("value"));
        ObservableList<Review> list= FXCollections.observableArrayList(reviewList.getReviews());
        reviews_table.setItems(list);
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
            movie = (Movie) user_movies_table.getSelectionModel().getSelectedItem();
            if (event.getClickCount()==2){

                //movie = (Movie) user_movies_table.getSelectionModel().getSelectedItem();
                System.out.println("TITUL "  + movie.getTitle()) ;

            }
        } catch (Exception e){
            BeanInvoker.logger.log(Level.WARNING,"ERROR",e.getMessage());
            e.printStackTrace();
        }
        new FilmDetailView();
    }

    @FXML
    public void getReviewFromTable(MouseEvent event) {
        try {
            if (event.getClickCount()==2){
                review_detail.setText(((Review) reviews_table.getSelectionModel().getSelectedItem()).getDetailr());
            }
        } catch (Exception e){
            BeanInvoker.logger.log(Level.WARNING,"ERROR",e.getMessage());
            e.printStackTrace();
        }

    }

    public void insertMovieDetail() {
        ObservableList<String> actorList = FXCollections.observableArrayList();

        filmDetail_title_field.setText(movie.getTitle());
        filmDetail_director_field.setText(movie.getDirector());
        filmDetail_rating_field.setText(""+movie.getRating());
        filmDetail_year_field.setText(movie.getYear());
        filmDetail_genre_field.setText(movie.getGenre());
        LinkedList<String> list= BeanInvoker.invokeMovieDetails(movie.getTitle(),id);
        //String string=list.get(2);
        filmDetail_myRating_field.setText(list.get(0));
        filmDetail_ratingDetail_field.setText(list.get(1));

        for (int i = 2; i < list.size() ; i++) {

            actorList.add(list.get(i));

           // string += list.get(i)+" , ";
        }
        System.out.println("actor " + actorList.get(0));
        filmDetail_actors_field.getItems().setAll(actorList);
        //filmDetail_actors_field.setText(string);

    }

    @FXML
    public void setRating(){

        turboWatch=ResourceBundle.getBundle("turboWatch");

        if (filmDetail_myRating_field.getText()==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rating");
            alert.setHeaderText(turboWatch.getString("zmenaRatingu"));
            alert.showAndWait();
            return;
        }

        BeanInvoker.invokeMovieDetailsRating(Integer.parseInt(filmDetail_myRating_field.getText()),filmDetail_ratingDetail_field.getText(),id, movie.getTitle(),checkNumber);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rating");
        alert.setHeaderText(turboWatch.getString("zmenaRatingu"));
        alert.showAndWait();
    }

    @FXML
    public void getSearchWin(){
        new SearchFiltersView();
    }

    @FXML
    public void searchMovieOnline(){
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(movie.getLink());
            desktop.browse(oURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void searchDirectorOnline(){
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(movie.getDirLink());
            desktop.browse(oURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void searchActorOnline(){
        try {
            if(filmDetail_actors_field.getValue()!=null){
                String link = BeanInvoker.invokeMovieDetailsActorLink(filmDetail_actors_field.getValue());

                Desktop desktop = java.awt.Desktop.getDesktop();
                URI oURL = new URI(link);
                desktop.browse(oURL);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void searchMovies(){

        String filter;
        int check = 0 ;
        filter=" WHERE ";

        if(!searchFilters_title_field.getText().equals("")){
            filter += " LOWER(m2.title) like LOWER('%" + searchFilters_title_field.getText() + "%') ";
            check=1;
        }
        if(check==1){
            filter +=" AND ";
            check=0;
        }

        if (!searchFilters_genre_field.getText().equals("")){
            filter += " LOWER(m2.genre) like LOWER('%" + searchFilters_genre_field.getText() + "%') ";
            check=1;
        }
        if(check==1){
            filter +=" AND ";
            check=0;
        }

        if (!searchFilters_director_field.getText().equals("")){
            filter += " LOWER(d2.name) like LOWER('%" + searchFilters_director_field.getText() + "%') ";
            check=1;
        }
        if(check==1){
            filter +=" AND ";
            check=0;
        }

        if (!searchFilters_actors_field.getText().equals("")){
            filter += " LOWER(a2.name) like LOWER('%" + searchFilters_actors_field.getText() + "%') ";
            check=1;
        }
        if(check==1){
            filter +=" AND ";
            check=0;
        }

        if (!searchFilters_year_from_field.getText().equals("")){
            filter += " m2.year > '" + searchFilters_year_from_field.getText() + "' ";
            check=1;
        } else {
            filter += " m2.year > '1900' ";
            check=1;
        }
        filter +=" AND ";

        if (!searchFilters_year_to_field.getText().equals("")){
            filter += " m2.year < '" + searchFilters_year_to_field.getText() + "' ";
            check=1;
        } else {
            filter += " m2.year < '2050' ";
            check=1;
        }
        if (!searchFilters_year_to_field.getText().equals("")){
            filter += " AND '" + searchFilters_rating_field.getText() + "' <= ";
        } else {
            filter += " AND '0' <= ";

        }

        filterGlob=filter;
        System.out.println(filter);
        allMovies();


    }



}
