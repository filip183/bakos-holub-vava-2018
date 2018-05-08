package server.serverService;


import model.Movie;
import model.MovieList;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;

@Stateless
@Remote(MovieSearchBeanRemote.class)
public class MovieSearchBean implements MovieSearchBeanRemote {

    @Override
    public MovieList searchMovies(String filter) {

            ArrayList<Movie> movies = new ArrayList<>();
            Connection con = null;
            String driver = "org.postgresql.Driver";

            try {


                Class.forName(driver).newInstance();
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vava",
                        "postgres" , "heslo");

                Statement st = con.createStatement();

                String str= String.format("SELECT DISTINCT m2.title, m2.genre, m2.year, d2.name, r2.rating, m2.link, d2.link FROM movies m2 " +
                        " JOIN directors d2 ON m2.director_id = d2.id " +
                        " JOIN actedin a ON m2.id = a.movie_id " +
                        " JOIN actors a2 ON a.actor_id = a2.id " +
                        " LEFT JOIN reviews r2 ON m2.id = r2.movie_id " +
                        " %s (SELECT coalesce(AVG(r.rating),0) FROM movies m " +
                        " JOIN directors d2 ON m.director_id = d2.id " +
                        " JOIN actedin a ON m.id = a.movie_id " +
                        " JOIN actors a2 ON a.actor_id = a2.id " +
                        " LEFT JOIN reviews r ON m.id = r.movie_id " +
                        " %s  AND  m2.id=m.id " +
                        " GROUP BY m.id);",filter,filter.substring(0,filter.length()-11));
                System.out.println("QUERy "  + str);
                ResultSet rs = st.executeQuery(str);

                while(rs.next()){
                    movies.add (new Movie(rs.getString(1),rs.getString(2),
                            rs.getString(3),rs.getString(4),rs.getInt(5),
                            rs.getString(6),rs.getString(7)));
                }

                MovieList movieList = new MovieList(movies);
                return movieList;
            } catch (Exception e){

                ServerLogger.log(Level.INFO,"ERROR ",e);
                e.printStackTrace();
            }

            return null;

    }

}