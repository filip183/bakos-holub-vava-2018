package server.serverService;


import model.Movie;
import model.MovieList;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;


@Stateless
@Remote(UserMoviesBeanRemote.class)
public class UserMoviesBean implements UserMoviesBeanRemote{
  //  public static final ServerLogger logger=ServerLogger.getLogger(LoginBean.class.getName());

    @Override
    public MovieList getMovies(int id) {

        ArrayList<Movie> movies = new ArrayList<>();
        Connection con = null;
        String driver = "org.postgresql.Driver";

        try {
//            FileHandler fh = new FileHandler("C:\\Programovanie\\Java\\Vava20182\\resources\\ServerlogFile.txt", true);
//            logger.addHandler(fh);

            ServerLogger.log(Level.INFO,"FUNGUJE");
            ServerLogger.log(Level.FINE,"Log Funguje");

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vava",
                    "postgres" , "heslo");

            Statement st = con.createStatement();

            String str= String.format("SELECT m2.title, m2.genre, d2.name, m2.year, r.rating, m2.link, d2.link FROM reviews r " +
                    "JOIN movies m2 ON r.movie_id = m2.id " +
                    "JOIN directors d2 ON m2.director_id = d2.id " +
                    "WHERE user_id= %d;",id);
            System.out.println("QUERy "  + str);
            ResultSet rs = st.executeQuery(str);

            while(rs.next()){
                movies.add (new Movie(rs.getString(1),rs.getString(2),
                        rs.getString(4),rs.getString(3),rs.getInt(5),
                        rs.getString(6),rs.getString(7)));
            }

            MovieList movieList = new MovieList(movies);
            return movieList;
        } catch (Exception e){

            ServerLogger.log(Level.INFO,"ERROR ",e);
            e.printStackTrace();
        }
        return null;
        //return "User name " + user.getLogin();


    }
}
